package com.teamabode.rodsnreels.core.registry;

import com.teamabode.rodsnreels.RodsNReels;
import com.teamabode.rodsnreels.core.tag.RNRBiomeTags;
import com.teamabode.rodsnreels.core.tag.RNRBlockTags;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class RNRPlacedFeatures {
    public static final RegistryKey<PlacedFeature> BUBBLEDEW = create("bubbledew");
    public static final RegistryKey<PlacedFeature> SUSPICIOUS_GRAVEL = create("suspicious_gravel");

    public static void register(Registerable<PlacedFeature> registry) {
        var configuredFeatures = registry.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        PlacedFeatures.register(registry, BUBBLEDEW, configuredFeatures.getOrThrow(RNRConfiguredFeatures.BUBBLEDEW), List.of(
                NoiseBasedCountPlacementModifier.of(50, 80.0f, 0.0f),
                SquarePlacementModifier.of(),
                HeightmapPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR_WG),
                BlockFilterPlacementModifier.of(BlockPredicate.matchingBlockTag(new Vec3i(0, -1, 0), RNRBlockTags.BUBBLEDEW_GROWS_ON)),
                BiomePlacementModifier.of()
        ));
        PlacedFeatures.register(registry, SUSPICIOUS_GRAVEL, configuredFeatures.getOrThrow(RNRConfiguredFeatures.SUSPICIOUS_GRAVEL), List.of(
                CountPlacementModifier.of(24),
                SquarePlacementModifier.of(),
                HeightmapPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR_WG),
                RandomOffsetPlacementModifier.of(ConstantIntProvider.create(0), ConstantIntProvider.create(-1)),
                BlockFilterPlacementModifier.of(BlockPredicate.matchingBlocks(Blocks.GRAVEL)),
                BiomePlacementModifier.of()
        ));
    }

    private static RegistryKey<PlacedFeature> create(String id) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, RodsNReels.id(id));
    }

    public static void createBiomeModifications() {
        BiomeModifications.addFeature(BiomeSelectors.tag(RNRBiomeTags.HAS_RIVER_ARCHAEOLOGY), GenerationStep.Feature.VEGETAL_DECORATION, SUSPICIOUS_GRAVEL);
    }
}
