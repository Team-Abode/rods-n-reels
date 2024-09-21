package com.teamabode.rodsnreels.core.registry;

import com.teamabode.rodsnreels.RodsNReels;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.biome.Biome;

public class RNRBiomes {
    public static final RegistryKey<Biome> OCEAN_TRENCH = createKey("ocean_trench");

    private static RegistryKey<Biome> createKey(String name) {
        return RegistryKey.of(RegistryKeys.BIOME, RodsNReels.id(name));
    }
}
