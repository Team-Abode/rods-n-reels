package com.teamabode.rodsnreels.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import org.jetbrains.annotations.Nullable;

public class ZapParticle extends AnimatedParticle {

    public ZapParticle(ClientWorld clientWorld, double x, double y, double z, SpriteProvider spriteProvider) {
        super(clientWorld, x, y, z, spriteProvider, 0.0f);

        this.setAlpha(1.0f);
        this.scale = 0.25f;
        this.maxAge = 10;
        this.setSpriteForAge(spriteProvider);
        this.collidesWithWorld = false;
    }

    public static class Factory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider sprite;

        public Factory(SpriteProvider sprite) {
            this.sprite = sprite;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            return new ZapParticle(world, x, y, z, this.sprite);
        }
    }
}
