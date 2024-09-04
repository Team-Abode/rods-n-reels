package com.teamabode.rodsnreels.common.structure;

import com.mojang.serialization.MapCodec;
import com.teamabode.rodsnreels.RodsNReels;
import com.teamabode.rodsnreels.core.registry.RNRStructures;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;

import java.util.Optional;

public class Ruin extends Structure {
    public static final MapCodec<Ruin> CODEC = Ruin.simpleCodec(Ruin::new);

    public Ruin(StructureSettings structureSettings) {
        super(structureSettings);
    }

    @Override
    protected Optional<GenerationStub> findGenerationPoint(GenerationContext generationContext) {
        return Optional.of(new GenerationStub(generationContext.chunkPos().getBlockAt(0 , 63, 0), structurePiecesBuilder -> Ruin.generatePieces(structurePiecesBuilder, generationContext)));
    }

    @Override
    public StructureType<?> type() {
        return RNRStructures.RUIN;
    }

    private static void generatePieces(StructurePiecesBuilder structurePiecesBuilder, Structure.GenerationContext generationContext) {
        HolderGetter<StructureTemplatePool> pools = generationContext.registryAccess().lookupOrThrow(Registries.TEMPLATE_POOL);
        Holder<StructureTemplatePool> ruin_pillar_template_pool = pools.getOrThrow(ResourceKey.create(Registries.TEMPLATE_POOL, RodsNReels.id("ruin_pillar")));

        StructurePoolElement poolElement = ruin_pillar_template_pool.value().getRandomTemplate(generationContext.random());

        structurePiecesBuilder.addPiece(new PoolElementStructurePiece(
                generationContext.structureTemplateManager(),
                poolElement,
                generationContext.chunkPos().getBlockAt(0 , 63, 0), 2,
                Rotation.NONE,
                BoundingBox.infinite(),
                LiquidSettings.APPLY_WATERLOGGING
        ));
    }
}
