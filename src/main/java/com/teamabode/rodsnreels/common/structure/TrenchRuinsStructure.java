package com.teamabode.rodsnreels.common.structure;

import com.mojang.serialization.MapCodec;
import com.teamabode.rodsnreels.core.registry.RNRStructureTypes;
import com.teamabode.rodsnreels.core.registry.RNRTemplatePools;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureLiquidSettings;
import net.minecraft.structure.StructurePiecesCollector;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;

public class TrenchRuinsStructure extends Structure {
    public static final MapCodec<TrenchRuinsStructure> CODEC = createCodec(TrenchRuinsStructure::new);

    public TrenchRuinsStructure(Config structureSettings) {
        super(structureSettings);
    }

    @Override
    protected Optional<StructurePosition> getStructurePosition(Context generationContext) {
        return getStructurePosition(generationContext, Heightmap.Type.OCEAN_FLOOR_WG, (structurePiecesBuilder) -> {
            generatePieces(structurePiecesBuilder, generationContext);
        });
    }

    @Override
    public StructureType<?> getType() {
        return RNRStructureTypes.TRENCH_RUINS;
    }

    private static int getPlacementHeight(BlockPos basePos, StructurePoolElement poolElement, BlockRotation rotation, Structure.Context generationContext) {
        BlockBox boundingBox = poolElement.getBoundingBox(generationContext.structureTemplateManager(), basePos, rotation);

        List<BlockPos> corners = List.of(
                new BlockPos(boundingBox.getMinX(), 0, boundingBox.getMinZ()),
                new BlockPos(boundingBox.getMaxX(), 0, boundingBox.getMinZ()),
                new BlockPos(boundingBox.getMinX(), 0, boundingBox.getMaxZ()),
                new BlockPos(boundingBox.getMaxX(), 0, boundingBox.getMaxZ())
        );

        int y = Integer.MAX_VALUE;

        for(BlockPos corner: corners) {
            y = Integer.min(y, generationContext.chunkGenerator().getHeightOnGround(corner.getX(), corner.getZ(), Heightmap.Type.OCEAN_FLOOR_WG, generationContext.world(), generationContext.noiseConfig()));
        }

        return y;
    };

    private static void generatePieces(StructurePiecesCollector structurePiecesBuilder, Structure.Context generationContext) {
        RegistryEntryLookup<StructurePool> pools = generationContext.dynamicRegistryManager().getWrapperOrThrow(RegistryKeys.TEMPLATE_POOL);
        RegistryEntry<StructurePool> gatewayPool = pools.getOrThrow(RNRTemplatePools.TRENCH_RUINS_GATEWAYS);
        RegistryEntry<StructurePool> pillarPool = pools.getOrThrow(RNRTemplatePools.TRENCH_RUINS_PILLARS);

        int pieceCount = generationContext.random().nextBetweenExclusive(3, 6);

        ArrayList<BlockBox> boundingBoxes = new ArrayList<>();

        StructurePoolElement gateWayPoolElement = gatewayPool.value().getRandomElement(generationContext.random());

        BlockPos gatewayStartBlockPos = generationContext.chunkPos().getBlockPos(0, 0, 0);
        BlockRotation gatewayRotation = BlockRotation.random(generationContext.random());
        BlockPos gatewayBlockPos = new BlockPos(
                gatewayStartBlockPos.getX(),
                getPlacementHeight(gatewayStartBlockPos, gateWayPoolElement, gatewayRotation, generationContext) - 2,
                gatewayStartBlockPos.getZ()
        );

        BlockBox gateWayBoundingBox = gateWayPoolElement.getBoundingBox(generationContext.structureTemplateManager(), gatewayBlockPos, gatewayRotation);

        boundingBoxes.add(gateWayBoundingBox);

        structurePiecesBuilder.addPiece(new PoolStructurePiece(
                generationContext.structureTemplateManager(),
                gateWayPoolElement,
                gatewayBlockPos,
                0,
                gatewayRotation,
                BlockBox.infinite(),
                StructureLiquidSettings.APPLY_WATERLOGGING
        ));

        for(int i = 0; i < pieceCount; i++) {
            StructurePoolElement poolElement = pillarPool.value().getRandomElement(generationContext.random());

            BlockPos placeBlockPos = null;
            BlockRotation rotation = BlockRotation.random(generationContext.random());

            for(int attempt = 0; attempt < 5; attempt++) {
                BlockPos startBlockPos = generationContext.chunkPos().getBlockPos(generationContext.random().nextBetweenExclusive(-20, 20), 0, generationContext.random().nextBetweenExclusive(-20, 20));

                BlockPos groundBlockPos = new BlockPos(
                        startBlockPos.getX(),
                        getPlacementHeight(startBlockPos, poolElement, rotation, generationContext) - 2,
                        startBlockPos.getZ()
                );

                BlockBox boundingBox = poolElement.getBoundingBox(generationContext.structureTemplateManager(), groundBlockPos, rotation);

                boolean collided = false;

                for(BlockBox otherBoundingBox: boundingBoxes) {
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

            structurePiecesBuilder.addPiece(new PoolStructurePiece(
                    generationContext.structureTemplateManager(),
                    poolElement,
                    placeBlockPos,
                    0,
                    rotation,
                    BlockBox.infinite(),
                    StructureLiquidSettings.APPLY_WATERLOGGING
            ));
        }
    }
}
