package com.teamabode.rodsnreels.common.entity.goldfish;

import net.minecraft.entity.Bucketable;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;

public class Goldfish extends WaterCreatureEntity implements Bucketable {

    public Goldfish(EntityType<? extends Goldfish> entityType, World level) {
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

    public boolean unableToFollowOwner() {
        return this.hasVehicle() || this.isPanicking() /*|| this.getOwner() != null && this.getOwner().isSpectator()*/;
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
    public boolean isFromBucket() {
        return false;
    }

    @Override
    public void setFromBucket(boolean bl) {

    }

    @Override
    public void copyDataToStack(ItemStack stack) {

    }

    @Override
    public void copyDataFromNbt(NbtCompound tag) {

    }

    @Override
    public ItemStack getBucketItem() {
        return null;
    }

    @Override
    public SoundEvent getBucketFillSound() {
        return null;
    }
}
