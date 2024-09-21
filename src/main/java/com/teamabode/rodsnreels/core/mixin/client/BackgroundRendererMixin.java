package com.teamabode.rodsnreels.core.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.teamabode.rodsnreels.client.RodsNReelsClient;
import com.teamabode.rodsnreels.core.registry.RNRBiomes;
import net.minecraft.block.enums.CameraSubmersionType;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.FogShape;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BackgroundRenderer.class)
public class BackgroundRendererMixin {
    @Shadow private static float red;
    @Shadow private static float blue;
    @Shadow private static float green;

    @Unique
    private static float darkness = 0.0f;

    @Inject(method = "applyFog", at = @At("TAIL"))
    private static void rodsnreels$applyFog(Camera camera, BackgroundRenderer.FogType fogMode, float f, boolean bl, float g, CallbackInfo ci) {
        if(!RodsNReelsClient.applyOceanTrenchEffect) return;

        RenderSystem.setShaderFogShape(FogShape.SPHERE);
        RenderSystem.setShaderFogStart(0f);
        RenderSystem.setShaderFogEnd(RodsNReelsClient.getOceanTrenchFogDistance((float) camera.getPos().y));
    }

    @Inject(method = "render", at = @At("TAIL"))
    private static void rodsnreels$render(Camera camera, float f, ClientWorld clientLevel, int i, float g, CallbackInfo ci) {
        RodsNReelsClient.applyOceanTrenchEffect = camera.getSubmersionType() == CameraSubmersionType.WATER && clientLevel.getBiome(camera.getBlockPos()).matchesKey(RNRBiomes.OCEAN_TRENCH) && !((LivingEntity)camera.getFocusedEntity()).hasStatusEffect(StatusEffects.NIGHT_VISION) && !((LivingEntity)camera.getFocusedEntity()).hasStatusEffect(StatusEffects.BLINDNESS) && !((LivingEntity)camera.getFocusedEntity()).hasStatusEffect(StatusEffects.DARKNESS);

        darkness = RodsNReelsClient.getOceanTrenchFogDarkness((float) camera.getPos().y);
    }

    @Inject(method = "applyFogColor", at = @At("TAIL"))
    private static void rodsnreels$applyFogColor(CallbackInfo ci) {
        if(!RodsNReelsClient.applyOceanTrenchEffect) return;

        RenderSystem.setShaderFogColor(red * darkness, green * darkness, blue * darkness);
        RenderSystem.clearColor(red * darkness, green * darkness, blue * darkness, 0f);
    }
}
