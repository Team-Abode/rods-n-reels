package com.teamabode.rodsnreels.core.registry;

import com.teamabode.rodsnreels.RodsNReels;
import com.teamabode.rodsnreels.common.feature.SuspiciousBlockConfig;
import com.teamabode.rodsnreels.common.feature.SuspiciousBlockFeature;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

public class RNRFeatures {
    public static final Feature<SuspiciousBlockConfig> SUSPICIOUS_BLOCK = register("suspicious_block", new SuspiciousBlockFeature());

    public static void register() {}

    private static <FC extends FeatureConfig> Feature<FC> register(String name, Feature<FC> feature) {
        return Registry.register(Registries.FEATURE, RodsNReels.id(name), feature);
    }
}
