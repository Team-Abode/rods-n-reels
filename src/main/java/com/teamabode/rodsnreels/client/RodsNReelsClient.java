package com.teamabode.rodsnreels.client;

import com.teamabode.rodsnreels.RodsNReels;
import com.teamabode.rodsnreels.core.registry.RNRBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;

public class RodsNReelsClient implements ClientModInitializer {
    public static boolean applyOceanTrenchEffect = false;

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(RNRBlocks.BUBBBLEDEW_STEM, RenderType.cutout());
    }

    public static float getOceanTrenchFogDistance(float y) {
        y += 64f;

        return (float) (Math.pow(y, 1.2f) / 20f + 10f);
    }

    public static float getOceanTrenchFogDarkness(float y) {
        return Math.clamp((y / 63f - 0.15f) * (1f / (1f - 0.15f)), 0f, 1f);
    }
}
