package com.teamabode.rodsnreels.core.mixin.client;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import com.teamabode.rodsnreels.RodsNReels;
import com.teamabode.rodsnreels.client.RodsNReelsClient;
import com.teamabode.rodsnreels.core.registry.RNRBiomes;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.world.level.material.FogType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FogRenderer.class)
public class FogRendererMixin {
    @Shadow private static float fogRed;
    @Shadow private static float fogBlue;
    @Shadow private static float fogGreen;

    private static float darkness;

    @Inject(method = "setupFog", at = @At("TAIL"))
    private static void setupFog(Camera camera, FogRenderer.FogMode fogMode, float f, boolean bl, float g, CallbackInfo ci) {
        if(!RodsNReelsClient.applyOceanTrenchEffect) return;

        RenderSystem.setShaderFogShape(FogShape.SPHERE);
        RenderSystem.setShaderFogStart(-1f);
        RenderSystem.setShaderFogEnd(RodsNReelsClient.getOceanTrenchFogDistance((float) camera.getPosition().y));
    }

    @Inject(method = "setupColor", at = @At("TAIL"))
    private static void setupColor(Camera camera, float f, ClientLevel clientLevel, int i, float g, CallbackInfo ci) {
        RodsNReelsClient.applyOceanTrenchEffect = camera.getFluidInCamera() == FogType.WATER && clientLevel.getBiome(camera.getBlockPosition()).is(RNRBiomes.OCEAN_TRENCH);

        darkness = RodsNReelsClient.getOceanTrenchFogDarkness((float) camera.getPosition().y);
    }

    @Inject(method = "levelFogColor", at = @At("TAIL"))
    private static void setupColor(CallbackInfo ci) {
        if(!RodsNReelsClient.applyOceanTrenchEffect) return;

        RenderSystem.setShaderFogColor(fogRed * darkness, fogGreen * darkness, fogBlue * darkness);
        RenderSystem.clearColor(fogRed * darkness, fogGreen * darkness, fogBlue * darkness, 0f);
    }
}
