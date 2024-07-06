package com.teamabode.rodsnreels.core.mixin.client;

import com.teamabode.rodsnreels.client.RodsNReelsClient;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.world.level.material.FogType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LevelRenderer.class)
public class LevelRendererMixin {
    @Inject(method = "doesMobEffectBlockSky", at = @At("RETURN"), cancellable = true)
    private void doesMobEffectBlockSky(Camera camera, CallbackInfoReturnable<Boolean> cir) {
        if(!RodsNReelsClient.applyOceanTrenchEffect) return;

        cir.setReturnValue(true);
    }
}
