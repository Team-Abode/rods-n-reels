package com.teamabode.rodsnreels.client;

import com.teamabode.rodsnreels.RodsNReels;
import net.fabricmc.api.ClientModInitializer;

public class RodsNReelsClient implements ClientModInitializer {
    public static boolean applyOceanTrenchEffect = false;

    @Override
    public void onInitializeClient() {

    }

    public static float getOceanTrenchFogDistance(float y) {
        y += 64f;

        return (float) (Math.pow(y, 1.2f) / 20f + 10f);
    }

    public static float getOceanTrenchFogDarkness(float y) {
        return Math.clamp((y / 63f - 0.15f) * (1f / (1f - 0.15f)), 0f, 1f);
    }
}
