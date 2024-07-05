package com.teamabode.rodsnreels.core.registry;

import com.teamabode.rodsnreels.RodsNReels;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.entity.DecoratedPotPattern;

public class RNRDecoratedPotPatterns {

    public static final ResourceKey<DecoratedPotPattern> GILLS_POTTERY_PATTERN = createKey("gills");;



    public static DecoratedPotPattern register(Registry<DecoratedPotPattern> registry, ResourceKey<DecoratedPotPattern> resourceKey, String string) {
        return (DecoratedPotPattern)Registry.register(registry, resourceKey, new DecoratedPotPattern(RodsNReels.id(string)));
    }

    private static ResourceKey<DecoratedPotPattern> createKey(String name) {
        return ResourceKey.create(Registries.DECORATED_POT_PATTERN, RodsNReels.id(name));
    }

    public static void registerPotPatterns() {
    }
}
