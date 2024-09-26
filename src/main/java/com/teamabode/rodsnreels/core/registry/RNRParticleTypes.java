package com.teamabode.rodsnreels.core.registry;

import com.teamabode.rodsnreels.RodsNReels;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class RNRParticleTypes {

    private static <E extends ParticleEffect, T extends ParticleType<E>> T register(String name, T particleType) {
        return Registry.register(Registries.PARTICLE_TYPE, RodsNReels.id(name), particleType);
    }

    public static void register() {}
}
