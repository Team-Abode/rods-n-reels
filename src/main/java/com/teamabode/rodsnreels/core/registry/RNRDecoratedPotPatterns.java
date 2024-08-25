package com.teamabode.rodsnreels.core.registry;

import com.teamabode.rodsnreels.RodsNReels;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.entity.DecoratedPotPattern;

public class RNRDecoratedPotPatterns {
    public static final ResourceKey<DecoratedPotPattern> GILLS = createKey("gills");;

    public static void register() {
        register(GILLS, "gills_pottery_pattern");
    }

    private static ResourceKey<DecoratedPotPattern> createKey(String name) {
        return ResourceKey.create(Registries.DECORATED_POT_PATTERN, RodsNReels.id(name));
    }

    private static DecoratedPotPattern register(ResourceKey<DecoratedPotPattern> key, String name) {
        return Registry.register(BuiltInRegistries.DECORATED_POT_PATTERN, key, new DecoratedPotPattern(RodsNReels.id(name)));
    }
}
