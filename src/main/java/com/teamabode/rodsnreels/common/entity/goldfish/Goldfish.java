package com.teamabode.rodsnreels.common.entity.goldfish;

import com.teamabode.rodsnreels.common.entity.goldfish.goals.SwimTowardsOwnerGoal;
import com.teamabode.rodsnreels.core.registry.RNRItems;
import com.teamabode.rodsnreels.core.registry.RNRSoundEvents;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.UUID;

public class Goldfish extends AbstractFish implements OwnableEntity {
    private static final EntityDataAccessor<Optional<UUID>> OWNER_ID = SynchedEntityData.defineId(Goldfish.class, EntityDataSerializers.OPTIONAL_UUID);

    public Goldfish(EntityType<? extends Goldfish> entityType, Level level) {
        super(entityType, level);
    }

    public void spawnHeartParticles() {
        for(int i = 0; i < 7; i++) {
            double d = this.random.nextGaussian() * 0.02;
            double e = this.random.nextGaussian() * 0.02;
            double f = this.random.nextGaussian() * 0.02;
            this.level().addParticle(ParticleTypes.HEART, this.getRandomX(1.0), this.getRandomY() + 0.5, this.getRandomZ(1.0), d, e, f);
        }
    }

    public boolean unableToFollowOwner() {
        return this.isPassenger() || this.isPanicking() || this.getOwner() != null && this.getOwner().isSpectator();
    }

    @Override
    public void handleEntityEvent(byte flag) {
        if (flag == 7) {
            this.spawnHeartParticles();
        }
        else {
            super.handleEntityEvent(flag);
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(OWNER_ID, Optional.empty());
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        if (this.getOwnerUUID() != null) {
            compoundTag.putUUID("owner", this.getOwnerUUID());
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        if (compoundTag.hasUUID("owner")) {
            this.setOwnerUUID(compoundTag.getUUID("owner"));
        }
    }

    @Override
    public void saveToBucketTag(ItemStack stack) {
        Bucketable.saveDefaultDataToBucketTag(this, stack);
        CustomData.update(DataComponents.BUCKET_ENTITY_DATA, stack, (tag) -> {
            if (this.getOwnerUUID() != null) {
                tag.putUUID("owner", this.getOwnerUUID());
            }
        });
    }

    @Override
    public void loadFromBucketTag(CompoundTag tag) {
        Bucketable.loadDefaultDataFromBucketTag(this, tag);
        if (tag.contains("owner")) {
            this.setOwnerUUID(tag.getUUID("owner"));
        }
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25));
        this.goalSelector.addGoal(2, new RandomSwimmingGoal(this, 1.0f, 40));
        this.goalSelector.addGoal(4, new SwimTowardsOwnerGoal(this, 10));
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(RNRItems.GOLDFISH_BUCKET);
    }

    @Override
    public SoundEvent getAmbientSound() {
        return RNRSoundEvents.ENTITY_GOLDFISH_AMBIENT;
    }

    @Override
    public SoundEvent getDeathSound() {
        return RNRSoundEvents.ENTITY_GOLDFISH_DEATH;
    }

    @Override
    public SoundEvent getHurtSound(DamageSource damageSource) {
        return RNRSoundEvents.ENTITY_GOLDFISH_HURT;
    }

    @Override
    public SoundEvent getFlopSound() {
        return RNRSoundEvents.ENTITY_GOLDFISH_FLOP;
    }

    @Nullable
    @Override
    public UUID getOwnerUUID() {
        return this.entityData.get(OWNER_ID).orElse(null);
    }

    public void setOwnerUUID(UUID id) {
        this.entityData.set(OWNER_ID, Optional.ofNullable(id));
    }
}
