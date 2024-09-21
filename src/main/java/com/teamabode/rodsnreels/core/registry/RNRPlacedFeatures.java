package com.teamabode.rodsnreels.core.registry;

import com.teamabode.rodsnreels.RodsNReels;
import com.teamabode.rodsnreels.core.tag.RNRBiomeTags;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

public class RNRPlacedFeatures {
    public static final RegistryKey<PlacedFeature> SUSPICIOUS_GRAVEL = create("suspicious_gravel");

    private static RegistryKey<PlacedFeature> create(String id) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, RodsNReels.id(id));
    }

    public static void createBiomeModifications() {
        BiomeModifications.addFeature(BiomeSelectors.tag(RNRBiomeTags.HAS_SUSPICIOUS_BLOCK), GenerationStep.Feature.VEGETAL_DECORATION, SUSPICIOUS_GRAVEL);
    }
}
