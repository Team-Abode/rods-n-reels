package com.teamabode.rodsnreels.common.entity.goldfish;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Goldfish extends WaterAnimal implements Bucketable {

    public Goldfish(EntityType<? extends Goldfish> entityType, Level level) {
        super(entityType, level);
    }

    public void spawnHeartParticles() {
        for (int i = 0; i < 7; i++) {
            double deltaX = this.random.nextGaussian() * 0.02;
            double deltaY = this.random.nextGaussian() * 0.02;
            double deltaZ = this.random.nextGaussian() * 0.02;
            this.level().addParticle(ParticleTypes.HEART, this.getRandomX(1.0), this.getRandomY() + 0.5, this.getRandomZ(1.0), deltaX, deltaY, deltaZ);
        }
    }

    public boolean unableToFollowOwner() {
        return this.isPassenger() || this.isPanicking() /*|| this.getOwner() != null && this.getOwner().isSpectator()*/;
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
    public boolean fromBucket() {
        return false;
    }

    @Override
    public void setFromBucket(boolean bl) {

    }

    @Override
    public void saveToBucketTag(ItemStack stack) {

    }

    @Override
    public void loadFromBucketTag(CompoundTag tag) {

    }

    @Override
    public ItemStack getBucketItemStack() {
        return null;
    }

    @Override
    public SoundEvent getPickupSound() {
        return null;
    }
}
