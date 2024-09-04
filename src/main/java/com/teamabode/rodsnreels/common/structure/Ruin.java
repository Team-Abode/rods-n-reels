package com.teamabode.rodsnreels.common.structure;

import com.mojang.serialization.MapCodec;
import com.teamabode.rodsnreels.RodsNReels;
import com.teamabode.rodsnreels.core.registry.RNRStructures;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;

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
        RodsNReels.LOGGER.info(String.valueOf(generationContext.chunkPos()));
    }
}
