package com.teamabode.rodsnreels.client.renderer;

import com.teamabode.rodsnreels.RodsNReels;
import com.teamabode.rodsnreels.client.model.GoldfishEntityModel;
import com.teamabode.rodsnreels.common.entity.goldfish.variant.GoldfishBreed;
import com.teamabode.rodsnreels.common.entity.goldfish.variant.GoldfishColor;
import com.teamabode.rodsnreels.common.entity.goldfish.GoldfishEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class GoldfishEntityRenderer extends MobEntityRenderer<GoldfishEntity, SinglePartEntityModel<GoldfishEntity>> {
    private final SinglePartEntityModel<GoldfishEntity> ranchuModel;
    private final SinglePartEntityModel<GoldfishEntity> bubbleEyeModel;

    public GoldfishEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new GoldfishEntityModel(context.getPart(GoldfishEntityModel.RANCHU_LAYER)), 0.25f);

        this.ranchuModel = new GoldfishEntityModel(context.getPart(GoldfishEntityModel.RANCHU_LAYER));
        this.bubbleEyeModel = new GoldfishEntityModel(context.getPart(GoldfishEntityModel.BUBBLE_EYE_LAYER));
    }

    public SinglePartEntityModel<GoldfishEntity> getModel(GoldfishBreed breed) {
        return switch (breed) {
            case RANCHU -> this.ranchuModel;
            case BUBBLE_EYE -> this.bubbleEyeModel;
        };
    }

    @Override
    public void render(GoldfishEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        this.model = this.getModel(entity.getBreed());
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    protected void setupTransforms(GoldfishEntity goldfish, MatrixStack matrixStack, float f, float g, float h, float i) {
        super.setupTransforms(goldfish, matrixStack, f, g, h, i);

        float rot = 4.3F * MathHelper.sin(0.6F * f);
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(rot));

        if (!goldfish.isTouchingWater()) {
            matrixStack.translate(0.125F, 0.1F, 0.05F);
            matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(90.0F));
        }
    }

    @Override
    public Identifier getTexture(GoldfishEntity entity) {
        GoldfishBreed breed = entity.getBreed();
        GoldfishColor color = entity.getColor();

        return RodsNReels.id("textures/entity/goldfish/" + color.getName() + "_" + breed.getName() + ".png");
    }
}
