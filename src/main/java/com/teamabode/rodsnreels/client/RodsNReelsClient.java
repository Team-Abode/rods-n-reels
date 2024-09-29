package com.teamabode.rodsnreels.client;

import com.teamabode.rodsnreels.client.model.GoldfishEntityModel;
import com.teamabode.rodsnreels.client.particle.ZapParticle;
import com.teamabode.rodsnreels.client.renderer.GoldfishEntityRenderer;
import com.teamabode.rodsnreels.core.registry.RNRBlocks;
import com.teamabode.rodsnreels.core.registry.RNREntityTypes;
import com.teamabode.rodsnreels.core.registry.RNRParticleTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;

public class RodsNReelsClient implements ClientModInitializer {
    public static boolean applyOceanTrenchEffect = false;

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(RNRBlocks.BUBBLEDEW_STEM_PLANT, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(RNRBlocks.BUBBLEDEW_STEM, RenderLayer.getCutout());

        EntityModelLayerRegistry.registerModelLayer(GoldfishEntityModel.RANCHU_LAYER, GoldfishEntityModel::getRanchuTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(GoldfishEntityModel.COMET_LAYER, GoldfishEntityModel::getCometTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(GoldfishEntityModel.BUBBLE_EYE_LAYER, GoldfishEntityModel::getBubbleEyeTexturedModelData);
        EntityRendererRegistry.register(RNREntityTypes.GOLDFISH, GoldfishEntityRenderer::new);

        ParticleFactoryRegistry.getInstance().register(RNRParticleTypes.ZAP, ZapParticle.Factory::new);
    }

    public static float getOceanTrenchFogDistance(float y) {
        y += 64f;

        return (float) (Math.pow(y, 1.2f) / 20f + 25f);
    }

    public static float getOceanTrenchFogDarkness(float y) {
        return Math.clamp((y / 63f - 0.15f) * (1f / (1f - 0.15f)), 0f, 1f);
    }
}
