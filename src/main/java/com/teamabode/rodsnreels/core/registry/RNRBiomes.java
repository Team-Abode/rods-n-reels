package com.teamabode.rodsnreels.core.registry;

import com.teamabode.rodsnreels.RodsNReels;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;

public class RNRBiomes {
    public static final ResourceKey<Biome> ULTRA_DEEP_OCEAN = ResourceKey.create(Registries.BIOME, RodsNReels.id("ultra_deep_ocean"));
    public static final Climate.Parameter ULTRA_DEEP_OCEAN_CONTINENTALNESS = Climate.Parameter.span(-0.90f, -0.80f);
}
