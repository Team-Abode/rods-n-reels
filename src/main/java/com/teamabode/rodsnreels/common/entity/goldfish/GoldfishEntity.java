package com.teamabode.rodsnreels.common.entity.goldfish;

import com.mojang.serialization.Dynamic;
import com.teamabode.rodsnreels.common.entity.goldfish.brain.GoldfishBrain;
import com.teamabode.rodsnreels.common.entity.goldfish.brain.GoldfishUtils;
import com.teamabode.rodsnreels.common.entity.goldfish.variant.GoldfishBreed;
import com.teamabode.rodsnreels.common.entity.goldfish.variant.GoldfishColor;
import com.teamabode.rodsnreels.common.entity.goldfish.variant.GoldfishData;
import com.teamabode.rodsnreels.core.registry.RNRItems;
import com.teamabode.rodsnreels.core.registry.RNRSoundEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Util;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.UUID;

public class GoldfishEntity extends FishEntity {
    public static final TrackedData<Integer> BREED = DataTracker.registerData(GoldfishEntity.class, TrackedDataHandlerRegistry.INTEGER);
    public static final TrackedData<Integer> COLOR = DataTracker.registerData(GoldfishEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public GoldfishEntity(EntityType<? extends GoldfishEntity> entityType, World level) {
        super(entityType, level);
    }

    public static DefaultAttributeContainer.Builder createGoldfishAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0d);
    }

    public void spawnHeartParticles() {
        for (int i = 0; i < 7; i++) {
            double deltaX = this.random.nextGaussian() * 0.02;
            double deltaY = this.random.nextGaussian() * 0.02;
            double deltaZ = this.random.nextGaussian() * 0.02;
            this.getWorld().addParticle(ParticleTypes.HEART, this.getParticleX(1.0), this.getRandomBodyY() + 0.5, this.getParticleZ(1.0), deltaX, deltaY, deltaZ);
        }
    }

    @Nullable
    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        EntityData data = super.initialize(world, difficulty, spawnReason, entityData);
        Random random = world.getRandom();

        if (data instanceof GoldfishData goldfishData) {
            this.setBreed(goldfishData.breed);
            this.setColor(goldfishData.color);
            return data;
        }
        this.setBreed(Util.getRandom(GoldfishBreed.values(), random));
        this.setColor(Util.getRandom(GoldfishColor.values(), random));
        return data;
    }

    @Override
    protected void mobTick() {
        this.getWorld().getProfiler().push("goldfishBrain");
        this.getBrain().tick((ServerWorld) this.getWorld(), this);
        this.getWorld().getProfiler().pop();
        this.getWorld().getProfiler().push("goldfishActivityUpdate");
        GoldfishBrain.updateActivities(this);
        this.getWorld().getProfiler().pop();
        super.mobTick();
    }

    @Override
    protected Brain.Profile<GoldfishEntity> createBrainProfile() {
        return GoldfishBrain.createProfile();
    }

    @Override
    protected Brain<?> deserializeBrain(Dynamic<?> dynamic) {
        return GoldfishBrain.create(this.createBrainProfile().deserialize(dynamic));
    }

    @Override
    public Brain<GoldfishEntity> getBrain() {
        return (Brain<GoldfishEntity>) super.getBrain();
    }

    @Override
    protected void initGoals() {
        // We will use the brain system, do NOT call the super method.
    }

    @Override
    public void handleStatus(byte flag) {
        if (flag == 7) {
            this.spawnHeartParticles();
        }
        else {
            super.handleStatus(flag);
        }
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(BREED, 0);
        builder.add(COLOR, 0);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("breed", this.getBreed().getId());
        nbt.putInt("color", this.getColor().getId());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setBreed(GoldfishBreed.byId(nbt.getInt("breed")));
        this.setColor(GoldfishColor.byId(nbt.getInt("color")));
    }

    @Override
    public void copyDataToStack(ItemStack stack) {
        Bucketable.copyDataToStack(this, stack);
        NbtComponent.set(DataComponentTypes.BUCKET_ENTITY_DATA, stack, nbt -> {
            Optional<UUID> likedPlayer = GoldfishUtils.getLikedPlayer(this);
            likedPlayer.ifPresent(value -> nbt.putUuid("liked_player", value));
            nbt.putInt("breed", this.getBreed().getId());
            nbt.putInt("color", this.getColor().getId());
        });
    }

    @Override
    public void copyDataFromNbt(NbtCompound nbt) {
        Bucketable.copyDataFromNbt(this, nbt);
        if (nbt.containsUuid("liked_player")) {
            GoldfishUtils.setLikedPlayer(this, nbt.getUuid("liked_player"));
        }
        if (nbt.contains("breed", NbtElement.INT_TYPE)) {
            int breed = nbt.getInt("breed");
            this.setBreed(GoldfishBreed.byId(breed));
        }
        if (nbt.contains("color", NbtElement.INT_TYPE)) {
            int color = nbt.getInt("color");
            this.setColor(GoldfishColor.byId(color));
        }
    }

    public GoldfishBreed getBreed() {
        return GoldfishBreed.byId(dataTracker.get(BREED));
    }

    public void setBreed(GoldfishBreed breed) {
        this.dataTracker.set(BREED, breed.getId());
    }

    public GoldfishColor getColor() {
        return GoldfishColor.byId(dataTracker.get(COLOR));
    }

    public void setColor(GoldfishColor color) {
        dataTracker.set(COLOR, color.getId());
    }

    @Override
    public ItemStack getBucketItem() {
        return new ItemStack(RNRItems.GOLDFISH_BUCKET);
    }

    @Override
    protected SoundEvent getFlopSound() {
        return RNRSoundEvents.ENTITY_GOLDFISH_FLOP;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return RNRSoundEvents.ENTITY_GOLDFISH_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return RNRSoundEvents.ENTITY_GOLDFISH_DEATH;
    }
}
