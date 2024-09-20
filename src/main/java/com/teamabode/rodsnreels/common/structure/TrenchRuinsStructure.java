package com.teamabode.rodsnreels.common.structure;

import com.mojang.serialization.MapCodec;
import com.teamabode.rodsnreels.RodsNReels;
import com.teamabode.rodsnreels.core.registry.RNRStructures;
import com.teamabode.rodsnreels.core.registry.RNRTemplatePools;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TrenchRuinsStructure extends Structure {
    public static final MapCodec<TrenchRuinsStructure> CODEC = simpleCodec(TrenchRuinsStructure::new);

    public TrenchRuinsStructure(StructureSettings structureSettings) {
        super(structureSettings);
    }

    @Override
    protected Optional<GenerationStub> findGenerationPoint(GenerationContext generationContext) {
        return onTopOfChunkCenter(generationContext, Heightmap.Types.OCEAN_FLOOR_WG, (structurePiecesBuilder) -> {
            generatePieces(structurePiecesBuilder, generationContext);
        });
    }

    @Override
    public StructureType<?> type() {
        return RNRStructures.TRENCH_RUINS;
    }

    private static int getPlacementHeight(BlockPos basePos, StructurePoolElement poolElement, Rotation rotation, Structure.GenerationContext generationContext) {
        BoundingBox boundingBox = poolElement.getBoundingBox(generationContext.structureTemplateManager(), basePos, rotation);

        List<BlockPos> corners = List.of(
                new BlockPos(boundingBox.minX(), 0, boundingBox.minZ()),
                new BlockPos(boundingBox.maxX(), 0, boundingBox.minZ()),
                new BlockPos(boundingBox.minX(), 0, boundingBox.maxZ()),
                new BlockPos(boundingBox.maxX(), 0, boundingBox.maxZ())
        );

        int y = Integer.MAX_VALUE;

        for(BlockPos corner: corners) {
            y = Integer.min(y, generationContext.chunkGenerator().getFirstFreeHeight(corner.getX(), corner.getZ(), Heightmap.Types.OCEAN_FLOOR_WG, generationContext.heightAccessor(), generationContext.randomState()));
        }

        return y;
    };

    private static void generatePieces(StructurePiecesBuilder structurePiecesBuilder, Structure.GenerationContext generationContext) {
        HolderGetter<StructureTemplatePool> pools = generationContext.registryAccess().lookupOrThrow(Registries.TEMPLATE_POOL);
        Holder<StructureTemplatePool> gatewayPool = pools.getOrThrow(RNRTemplatePools.TRENCH_RUINS_GATEWAYS);
        Holder<StructureTemplatePool> pillarPool = pools.getOrThrow(RNRTemplatePools.TRENCH_RUINS_PILLARS);

        int pieceCount = generationContext.random().nextInt(3, 6);

        ArrayList<BoundingBox> boundingBoxes = new ArrayList<>();

        StructurePoolElement gateWayPoolElement = gatewayPool.value().getRandomTemplate(generationContext.random());

        BlockPos gatewayStartBlockPos = generationContext.chunkPos().getBlockAt(0, 0, 0);
        Rotation gatewayRotation = Rotation.getRandom(generationContext.random());
        BlockPos gatewayBlockPos = new BlockPos(
                gatewayStartBlockPos.getX(),
                getPlacementHeight(gatewayStartBlockPos, gateWayPoolElement, gatewayRotation, generationContext) - 2,
                gatewayStartBlockPos.getZ()
        );

        BoundingBox gateWayBoundingBox = gateWayPoolElement.getBoundingBox(generationContext.structureTemplateManager(), gatewayBlockPos, gatewayRotation);

        boundingBoxes.add(gateWayBoundingBox);

        structurePiecesBuilder.addPiece(new PoolElementStructurePiece(
                generationContext.structureTemplateManager(),
                gateWayPoolElement,
                gatewayBlockPos,
                0,
                gatewayRotation,
                BoundingBox.infinite(),
                LiquidSettings.APPLY_WATERLOGGING
        ));

        for(int i = 0; i < pieceCount; i++) {
            StructurePoolElement poolElement = pillarPool.value().getRandomTemplate(generationContext.random());

            BlockPos placeBlockPos = null;
            Rotation rotation = Rotation.getRandom(generationContext.random());

            for(int attempt = 0; attempt < 5; attempt++) {
                BlockPos startBlockPos = generationContext.chunkPos().getBlockAt(generationContext.random().nextInt(-20, 20), 0, generationContext.random().nextInt(-20, 20));

                BlockPos groundBlockPos = new BlockPos(
                        startBlockPos.getX(),
                        getPlacementHeight(startBlockPos, poolElement, rotation, generationContext) - 2,
                        startBlockPos.getZ()
                );

                BoundingBox boundingBox = poolElement.getBoundingBox(generationContext.structureTemplateManager(), groundBlockPos, rotation);

                boolean collided = false;

                for(BoundingBox otherBoundingBox: boundingBoxes) {
                    if(otherBoundingBox.intersects(boundingBox)) {
                        collided = true;

                        break;
                    }
                }

                if(collided) continue;

                boundingBoxes.add(boundingBox);
                placeBlockPos = groundBlockPos;
            }

            if(placeBlockPos == null) continue;

            structurePiecesBuilder.addPiece(new PoolElementStructurePiece(
                    generationContext.structureTemplateManager(),
                    poolElement,
                    placeBlockPos,
                    0,
                    rotation,
                    BoundingBox.infinite(),
                    LiquidSettings.APPLY_WATERLOGGING
            ));
        }
    }
}
