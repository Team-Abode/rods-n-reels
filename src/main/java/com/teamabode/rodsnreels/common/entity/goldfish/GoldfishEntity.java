package com.teamabode.rodsnreels.common.entity.goldfish;

import com.mojang.serialization.Dynamic;
import com.teamabode.rodsnreels.core.registry.RNRItems;
import com.teamabode.rodsnreels.core.registry.RNRSoundEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class GoldfishEntity extends FishEntity {

    public GoldfishEntity(EntityType<? extends GoldfishEntity> entityType, World level) {
        super(entityType, level);
    }

    public void spawnHeartParticles() {
        for (int i = 0; i < 7; i++) {
            double deltaX = this.random.nextGaussian() * 0.02;
            double deltaY = this.random.nextGaussian() * 0.02;
            double deltaZ = this.random.nextGaussian() * 0.02;
            this.getWorld().addParticle(ParticleTypes.HEART, this.getParticleX(1.0), this.getRandomBodyY() + 0.5, this.getParticleZ(1.0), deltaX, deltaY, deltaZ);
        }
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
    public ItemStack getBucketItem() {
        return new ItemStack(RNRItems.GOLDFISH_BUCKET);
    }

    @Override
    public SoundEvent getBucketFillSound() {
        return SoundEvents.ITEM_BUCKET_FILL_FISH;
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
