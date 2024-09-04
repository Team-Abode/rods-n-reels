package com.teamabode.rodsnreels.common.structure;

import com.mojang.serialization.MapCodec;
import com.teamabode.rodsnreels.RodsNReels;
import com.teamabode.rodsnreels.core.registry.RNRStructures;
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
        structurePiecesBuilder.addPiece(new PoolElementStructurePiece(
                generationContext.structureTemplateManager(),
                SinglePoolElement.single(RodsNReels.id("ruin_pillar").toString()).apply(StructureTemplatePool.Projection.RIGID),
                generationContext.chunkPos().getBlockAt(0 , 63, 0), 2,
                Rotation.NONE,
                BoundingBox.infinite(),
                LiquidSettings.APPLY_WATERLOGGING
        ));
    }
}
