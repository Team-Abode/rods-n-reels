package com.teamabode.rodsnreels.core.registry;

import com.teamabode.rodsnreels.RodsNReels;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;

public class RNRBiomes {
    public static final ResourceKey<Biome> OCEAN_TRENCH = ResourceKey.create(Registries.BIOME, RodsNReels.id("ocean_trench"));
    public static final Climate.Parameter OCEAN_TRENCH_CONTINENTALNESS = Climate.Parameter.span(-0.90f, -0.70f);
}
