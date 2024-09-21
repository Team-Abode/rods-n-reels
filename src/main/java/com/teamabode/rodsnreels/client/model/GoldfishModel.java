package com.teamabode.rodsnreels.client.model;

import com.teamabode.rodsnreels.RodsNReels;
import com.teamabode.rodsnreels.common.entity.goldfish.Goldfish;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.util.math.MathHelper;

public class GoldfishModel extends SinglePartEntityModel<Goldfish> {
	public static final EntityModelLayer LAYER = new EntityModelLayer(RodsNReels.id("goldfish"), "main");

	private final ModelPart body;
	private final ModelPart leftFin;
	private final ModelPart rightFin;
	private final ModelPart head;
	private final ModelPart tailFin;

	public GoldfishModel(ModelPart root) {
		this.body = root.getChild("body");
		this.leftFin = body.getChild("left_fin");
		this.rightFin = body.getChild("right_fin");
		this.head = body.getChild("head");
		this.tailFin = body.getChild("tail_fin");
	}

	public static TexturedModelData createBodyLayer() {
		ModelData mesh = new ModelData();
		ModelPartData root = mesh.getRoot();

		ModelPartData body = root.addChild("body", ModelPartBuilder.create().uv(8, 10).cuboid(0.0F, 3.0F, -1.0F, 0.0F, 2.0F, 3.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(-2.0F, -2.0F, -4.0F, 4.0F, 5.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 20.0F, 0.0F));
		ModelPartData leftFin = body.addChild("left_fin", ModelPartBuilder.create()
				.uv(0, 1).cuboid(0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 1.0F, -4.0F, 0.0F, 0.3927F, 0.0F));
		ModelPartData rightFin = body.addChild("right_fin", ModelPartBuilder.create()
				.uv(0, -1).cuboid(0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, 1.0F, -4.0F, 0.0F, -0.3927F, 0.0F));
		ModelPartData head = body.addChild("head", ModelPartBuilder.create()
				.uv(0, 17).cuboid(-2.0F, -1.0F, -3.0F, 4.0F, 4.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, -4.0F));
		ModelPartData tailFin = body.addChild("tail_fin", ModelPartBuilder.create()
				.uv(14, 9).cuboid(1.0F, -4.0F, 1.0F, 0.0F, 5.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, 2.0F, 3.0F));
		return TexturedModelData.of(mesh, 32, 32);
	}

	@Override
	public void setAngles(Goldfish entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		float f = 1.0f;
		if (!entity.isTouchingWater()) {
			f = 1.5f;
		}
		this.tailFin.yaw = -f * 0.45f * MathHelper.sin(0.6f * animationProgress);
	}

	@Override
	public ModelPart getPart() {
		return this.body;
	}
}