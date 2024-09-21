package com.teamabode.rodsnreels.client.model;

import com.teamabode.rodsnreels.RodsNReels;
import com.teamabode.rodsnreels.common.entity.goldfish.Goldfish;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class GoldfishModel extends HierarchicalModel<Goldfish> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(RodsNReels.id("goldfish"), "main");

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

	public static LayerDefinition createBodyLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition root = mesh.getRoot();

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(8, 10).addBox(0.0F, 3.0F, -1.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.0F, -2.0F, -4.0F, 4.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 20.0F, 0.0F));
		PartDefinition leftFin = body.addOrReplaceChild("left_fin", CubeListBuilder.create()
				.texOffs(0, 1).addBox(0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 1.0F, -4.0F, 0.0F, 0.3927F, 0.0F));
		PartDefinition rightFin = body.addOrReplaceChild("right_fin", CubeListBuilder.create()
				.texOffs(0, -1).addBox(0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 1.0F, -4.0F, 0.0F, -0.3927F, 0.0F));
		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create()
				.texOffs(0, 17).addBox(-2.0F, -1.0F, -3.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -4.0F));
		PartDefinition tailFin = body.addOrReplaceChild("tail_fin", CubeListBuilder.create()
				.texOffs(14, 9).addBox(1.0F, -4.0F, 1.0F, 0.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 2.0F, 3.0F));
		return LayerDefinition.create(mesh, 32, 32);
	}

	@Override
	public void setupAnim(Goldfish entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float k = 1.0f;
		if (!entity.isInWater()) {
			k = 1.5f;
		}
		this.tailFin.yRot = -k * 0.45f * Mth.sin(0.6f * ageInTicks);
	}

	@Override
	public ModelPart root() {
		return this.body;
	}
}