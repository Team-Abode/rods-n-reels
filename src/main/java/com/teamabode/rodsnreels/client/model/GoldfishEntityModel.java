package com.teamabode.rodsnreels.client.model;

import com.teamabode.rodsnreels.RodsNReels;
import com.teamabode.rodsnreels.common.entity.goldfish.GoldfishEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.util.math.MathHelper;

public class GoldfishEntityModel extends SinglePartEntityModel<GoldfishEntity> {
    public static final EntityModelLayer RANCHU_LAYER = new EntityModelLayer(RodsNReels.id("goldfish"), "ranchu");
    public static final EntityModelLayer COMET_LAYER = new EntityModelLayer(RodsNReels.id("goldfish"), "comet");
    public static final EntityModelLayer BUBBLE_EYE_LAYER = new EntityModelLayer(RodsNReels.id("goldfish"), "bubble_eye");

    private final ModelPart root;
    private final ModelPart tail;

    public GoldfishEntityModel(ModelPart root) {
        super(RenderLayer::getEntityTranslucent);

        this.root = root;
        this.tail = root.getChild("tail");
    }

    public static TexturedModelData getRanchuTexturedModelData() {
        ModelData data = new ModelData();
        ModelPartData root = data.getRoot();

        root.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -3.0F, -4.0F, 4.0F, 5.0F, 8.0F, new Dilation(0.0F))
                .uv(0, 13).cuboid(-2.0F, -2.0F, -7.0F, 4.0F, 4.0F, 3.0F, new Dilation(0.0F))
                .uv(26, -3).mirrored().cuboid(0.0F, 2.0F, -2.0F, 0.0F, 2.0F, 3.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, 22.0F, 0.0F));
        root.addChild("left_fin", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, -1.0F, 0.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 23.0F, -4.0F, 0.0F, -0.7854F, 0.0F));
        root.addChild("right_fin", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-2.0F, -1.0F, 0.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-2.0F, 23.0F, -4.0F, 0.0F, 0.7854F, 0.0F));
        root.addChild("tail", ModelPartBuilder.create().uv(16, -4).mirrored().cuboid(0.0F, -3.0F, 0.0F, 0.0F, 5.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, 22.0F, 4.0F));
        return TexturedModelData.of(data, 32, 32);
    }

    public static TexturedModelData getCometTexturedModelData() {
        ModelData data = new ModelData();
        ModelPartData root = data.getRoot();

        root.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -3.0F, -4.0F, 4.0F, 5.0F, 8.0F, new Dilation(0.0F))
                .uv(0, 13).cuboid(-2.0F, -2.0F, -7.0F, 4.0F, 3.0F, 3.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(0.0F, -5.0F, -2.0F, 0.0F, 2.0F, 4.0F, new Dilation(0.0F))
                .uv(26, -3).mirrored().cuboid(0.0F, 2.0F, -2.0F, 0.0F, 3.0F, 3.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, 22.0F, 0.0F));
        root.addChild("left_fin", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, -1.0F, 0.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 22.0F, -3.0F, 0.0F, -0.7854F, 0.0F));
        root.addChild("right_fin", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-2.0F, -1.0F, 0.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-2.0F, 22.0F, -3.0F, 0.0F, 0.7854F, 0.0F));
        root.addChild("tail", ModelPartBuilder.create().uv(16, -4).mirrored().cuboid(0.0F, -4.0F, 0.0F, 0.0F, 7.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, 22.0F, 4.0F));
        return TexturedModelData.of(data, 32, 32);
    }

    public static TexturedModelData getBubbleEyeTexturedModelData() {
        ModelData data = new ModelData();
        ModelPartData root = data.getRoot();

        root.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -3.0F, -4.0F, 4.0F, 5.0F, 8.0F, new Dilation(0.0F))
                .uv(0, 13).cuboid(-1.0F, -1.0F, -7.0F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F))
                .uv(0, 18).mirrored().cuboid(1.0F, -2.0F, -7.0F, 2.0F, 3.0F, 3.0F, new Dilation(0.0F)).mirrored(false)
                .uv(0, 18).cuboid(-3.0F, -2.0F, -7.0F, 2.0F, 3.0F, 3.0F, new Dilation(0.0F))
                .uv(26, -3).mirrored().cuboid(0.0F, 2.0F, -2.0F, 0.0F, 3.0F, 3.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, 22.0F, 0.0F));
        root.addChild("left_fin", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, -1.0F, 0.0F, 3.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 22.0F, -3.0F, 0.0F, -0.7854F, 0.0F));
        root.addChild("right_fin", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-3.0F, -1.0F, 0.0F, 3.0F, 2.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-2.0F, 22.0F, -3.0F, 0.0F, 0.7854F, 0.0F));
        root.addChild("tail", ModelPartBuilder.create().uv(16, -4).mirrored().cuboid(0.0F, -3.0F, 0.0F, 0.0F, 5.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, 22.0F, 4.0F));
        return TexturedModelData.of(data, 32, 32);
    }

    @Override
    public void setAngles(GoldfishEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        float wagAngle = 1.0f;
        if (!entity.isTouchingWater()) {
            wagAngle = 1.5f;
        }
        this.tail.yaw = -wagAngle * 0.45f * MathHelper.sin(0.6f * animationProgress);
    }

    @Override
    public ModelPart getPart() {
        return this.root;
    }
}
