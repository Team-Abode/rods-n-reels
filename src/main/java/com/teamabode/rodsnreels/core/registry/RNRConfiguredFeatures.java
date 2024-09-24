package com.teamabode.rodsnreels.core.registry;

import com.teamabode.rodsnreels.RodsNReels;
import com.teamabode.rodsnreels.common.block.BubbledewStemBlock;
import com.teamabode.rodsnreels.common.feature.SuspiciousBlockConfig;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.BlockColumnFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;

import java.util.List;

public class RNRConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> BUBBLEDEW = createKey("bubbledew");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SUSPICIOUS_GRAVEL = createKey("suspiscious_gravel");

    public static void register(Registerable<ConfiguredFeature<?, ?>> registry) {
        ConfiguredFeatures.register(registry, BUBBLEDEW, Feature.BLOCK_COLUMN, createBubbledewConfig());
        ConfiguredFeatures.register(registry, SUSPICIOUS_GRAVEL, RNRFeatures.SUSPICIOUS_BLOCK, createSuspiciousGravelConfig());
    }

    private static BlockColumnFeatureConfig createBubbledewConfig() {
        return new BlockColumnFeatureConfig(List.of(
                new BlockColumnFeatureConfig.Layer(UniformIntProvider.create(8, 20), SimpleBlockStateProvider.of(RNRBlocks.BUBBLEDEW_STEM_PLANT)),
                new BlockColumnFeatureConfig.Layer(ConstantIntProvider.create(1), SimpleBlockStateProvider.of(RNRBlocks.BUBBLEDEW_STEM.getDefaultState().with(BubbledewStemBlock.ATTACHED, true))),
                new BlockColumnFeatureConfig.Layer(ConstantIntProvider.create(1), SimpleBlockStateProvider.of(RNRBlocks.BUBBLEDEW))

        ), Direction.UP, BlockPredicate.matchingBlocks(Blocks.WATER), true);
    }

    private static SuspiciousBlockConfig createSuspiciousGravelConfig() {
        return new SuspiciousBlockConfig(SimpleBlockStateProvider.of(Blocks.SUSPICIOUS_GRAVEL), RNRLootTables.RIVER_ARCHAEOLOGY);
    }

    private static RegistryKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, RodsNReels.id(name));
    }
}
