package com.teamabode.rodsnreels.client.renderer;

import com.teamabode.rodsnreels.RodsNReels;
import com.teamabode.rodsnreels.client.model.GoldfishModel;
import com.teamabode.rodsnreels.common.entity.goldfish.GoldfishEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class GoldfishRenderer extends MobEntityRenderer<GoldfishEntity, GoldfishModel> {
    public static final Identifier TEXTURE = RodsNReels.id("textures/entity/goldfish.png");

    public GoldfishRenderer(EntityRendererFactory.Context context) {
        super(context, new GoldfishModel(context.getPart(GoldfishModel.LAYER)), 0.25f);
    }

    @Override
    protected void setupTransforms(GoldfishEntity goldfish, MatrixStack matrixStack, float f, float g, float h, float i) {
        super.setupTransforms(goldfish, matrixStack, f, g, h, i);

        float rot = 4.3F * MathHelper.sin(0.6F * f);
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(rot));

        if (!goldfish.isTouchingWater()) {
            matrixStack.translate(0.25f, 0.125f, 0.0f);
            matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(90.0F));
        }
    }

    @Override
    public Identifier getTexture(GoldfishEntity entity) {
        return TEXTURE;
    }
}
