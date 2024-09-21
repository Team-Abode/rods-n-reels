package com.teamabode.rodsnreels.core.registry;

import com.teamabode.rodsnreels.RodsNReels;
import net.minecraft.block.DecoratedPotPattern;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class RNRDecoratedPotPatterns {
    public static final RegistryKey<DecoratedPotPattern> GILLS = createKey("gills");

    public static void register() {
        register(GILLS, "gills_pottery_pattern");
    }

    private static RegistryKey<DecoratedPotPattern> createKey(String name) {
        return RegistryKey.of(RegistryKeys.DECORATED_POT_PATTERN, RodsNReels.id(name));
    }

    private static DecoratedPotPattern register(RegistryKey<DecoratedPotPattern> key, String name) {
        return Registry.register(Registries.DECORATED_POT_PATTERN, key, new DecoratedPotPattern(RodsNReels.id(name)));
    }
}
