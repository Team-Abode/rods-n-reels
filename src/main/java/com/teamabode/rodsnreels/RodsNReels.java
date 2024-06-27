package com.teamabode.rodsnreels;

import com.google.common.reflect.Reflection;
import com.teamabode.rodsnreels.core.registry.RNRFeatures;
import com.teamabode.rodsnreels.core.registry.RNRPlacedFeatures;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;
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
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
    }
}
