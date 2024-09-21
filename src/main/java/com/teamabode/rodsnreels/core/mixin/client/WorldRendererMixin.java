package com.teamabode.rodsnreels.core.mixin.client;

import com.teamabode.rodsnreels.client.RodsNReelsClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
    @Inject(method = "hasBlindnessOrDarkness", at = @At("RETURN"), cancellable = true)
    private void rodsnreels$hasBlindnessOrDarkness(Camera camera, CallbackInfoReturnable<Boolean> cir) {
        if (!RodsNReelsClient.applyOceanTrenchEffect) return;

        cir.setReturnValue(true);
    }
}
