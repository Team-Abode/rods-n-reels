package com.teamabode.rodsnreels.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.teamabode.rodsnreels.RodsNReels;
import com.teamabode.rodsnreels.client.model.GoldfishModel;
import com.teamabode.rodsnreels.common.entity.goldfish.Goldfish;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class GoldfishRenderer extends MobRenderer<Goldfish, GoldfishModel> {
    public static final ResourceLocation TEXTURE = RodsNReels.id("textures/entity/goldfish.png");

    public GoldfishRenderer(EntityRendererProvider.Context context) {
        super(context, new GoldfishModel(context.bakeLayer(GoldfishModel.LAYER_LOCATION)), 0.4f);
    }

    @Override
    protected void setupRotations(Goldfish goldfish, PoseStack poseStack, float f, float g, float h, float i) {
        super.setupRotations(goldfish, poseStack, f, g, h, i);

        float rot = 4.3F * Mth.sin(0.6F * f);
        poseStack.mulPose(Axis.YP.rotationDegrees(rot));

        if (!goldfish.isInWater()) {
            poseStack.translate(0.1F, 0.1F, -0.1F);
            poseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
        }
    }

    @Override
    public ResourceLocation getTextureLocation(Goldfish entity) {
        return TEXTURE;
    }
}
