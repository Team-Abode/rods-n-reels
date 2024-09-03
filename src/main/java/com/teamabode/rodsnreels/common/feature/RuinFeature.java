package com.teamabode.rodsnreels.common.feature;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.teamabode.rodsnreels.RodsNReels;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.commands.PlaceCommand;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;

import java.util.Optional;

public class RuinFeature extends Feature<NoneFeatureConfiguration> {
    public RuinFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();

        HolderGetter<StructureTemplatePool> pools = context.level().registryAccess().lookupOrThrow(Registries.TEMPLATE_POOL);
        Holder<StructureTemplatePool> ruin_pillar_template_pool = pools.getOrThrow(ResourceKey.create(Registries.TEMPLATE_POOL, RodsNReels.id("ruin_pillar")));

        StructurePoolElement poolElement = ruin_pillar_template_pool.value().getRandomTemplate(context.random());
        poolElement.place(level.getLevel().getStructureManager(), level, level.getLevel().structureManager(), context.chunkGenerator(), pos, pos, Rotation.NONE, BoundingBox.infinite(), context.random(), LiquidSettings.APPLY_WATERLOGGING, false);

        return true;
    }
}
