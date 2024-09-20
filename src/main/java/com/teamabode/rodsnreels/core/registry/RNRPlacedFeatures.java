package com.teamabode.rodsnreels.core.registry;

import com.teamabode.rodsnreels.RodsNReels;
import com.teamabode.rodsnreels.core.tag.RNRBiomeTags;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class RNRPlacedFeatures {
    public static final ResourceKey<PlacedFeature> SUSPICIOUS_GRAVEL = create("suspicious_gravel");

    private static ResourceKey<PlacedFeature> create(String id) {
        return ResourceKey.create(Registries.PLACED_FEATURE, RodsNReels.id(id));
    }

    public static void createBiomeModifications() {
        BiomeModifications.addFeature(BiomeSelectors.tag(RNRBiomeTags.HAS_SUSPICIOUS_BLOCK), GenerationStep.Decoration.VEGETAL_DECORATION, SUSPICIOUS_GRAVEL);
    }
}
