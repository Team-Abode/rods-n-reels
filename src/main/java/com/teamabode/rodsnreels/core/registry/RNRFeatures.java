package com.teamabode.rodsnreels.core.registry;

import com.teamabode.rodsnreels.RodsNReels;
import com.teamabode.rodsnreels.common.feature.RuinFeature;
import com.teamabode.rodsnreels.common.feature.SuspiciousBlockConfiguration;
import com.teamabode.rodsnreels.common.feature.SuspiciousBlockFeature;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class RNRFeatures {
    public static final Feature<SuspiciousBlockConfiguration> SUSPICIOUS_BLOCK = register("suspicious_block", new SuspiciousBlockFeature());
    public static final Feature<NoneFeatureConfiguration> RUIN = register("ruin", new RuinFeature());

    public static void register() {}

    private static <FC extends FeatureConfiguration> Feature<FC> register(String name, Feature<FC> feature) {
        return Registry.register(BuiltInRegistries.FEATURE, RodsNReels.id(name), feature);
    }
}
