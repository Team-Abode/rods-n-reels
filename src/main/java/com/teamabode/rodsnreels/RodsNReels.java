package com.teamabode.rodsnreels;

import com.google.common.reflect.Reflection;
import com.teamabode.rodsnreels.core.registry.RNRFeatures;
import com.teamabode.rodsnreels.core.registry.RNRPlacedFeatures;
import com.teamabode.rodsnreels.core.registry.tag.RNRBiomeTags;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModification;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RodsNReels implements ModInitializer {
    public static final String MOD_ID = "rods_n_reels";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public void onInitialize() {
        Reflection.initialize(RNRFeatures.class);
        RNRPlacedFeatures.createBiomeModifications();
    }

    public static ResourceLocation id(String name) {
        return new ResourceLocation(MOD_ID, name);
    }
}
