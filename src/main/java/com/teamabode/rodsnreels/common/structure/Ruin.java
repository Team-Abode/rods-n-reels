package com.teamabode.rodsnreels.common.structure;

import com.mojang.serialization.MapCodec;
import com.teamabode.rodsnreels.RodsNReels;
import com.teamabode.rodsnreels.core.registry.RNRStructures;
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
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;

import java.util.ArrayList;
import java.util.Optional;

public class Ruin extends Structure {
    public static final MapCodec<Ruin> CODEC = Ruin.simpleCodec(Ruin::new);

    public Ruin(StructureSettings structureSettings) {
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
        return RNRStructures.RUIN;
    }

    private static void generatePieces(StructurePiecesBuilder structurePiecesBuilder, Structure.GenerationContext generationContext) {
        HolderGetter<StructureTemplatePool> pools = generationContext.registryAccess().lookupOrThrow(Registries.TEMPLATE_POOL);
        Holder<StructureTemplatePool> ruin_gateway_template_pool = pools.getOrThrow(ResourceKey.create(Registries.TEMPLATE_POOL, RodsNReels.id("ruin_gateway")));
        Holder<StructureTemplatePool> ruin_pillar_template_pool = pools.getOrThrow(ResourceKey.create(Registries.TEMPLATE_POOL, RodsNReels.id("ruin_pillar")));

        int pieceCount = generationContext.random().nextInt(3, 6);

        ArrayList<BoundingBox> boundingBoxes = new ArrayList<>();

        StructurePoolElement gateWayPoolElement = ruin_gateway_template_pool.value().getRandomTemplate(generationContext.random());

        BlockPos gatewayStartBlockPos = generationContext.chunkPos().getBlockAt(0, 0, 0);
        BlockPos gatewayBlockPos = new BlockPos(
                gatewayStartBlockPos.getX(),
                generationContext.chunkGenerator().getFirstFreeHeight(gatewayStartBlockPos.getX(), gatewayStartBlockPos.getZ(), Heightmap.Types.OCEAN_FLOOR_WG, generationContext.heightAccessor(), generationContext.randomState()) - 2,
                gatewayStartBlockPos.getZ()
        );

        BoundingBox gateWayBoundingBox = gateWayPoolElement.getBoundingBox(generationContext.structureTemplateManager(), gatewayBlockPos, Rotation.NONE);

        boundingBoxes.add(gateWayBoundingBox);

        structurePiecesBuilder.addPiece(new PoolElementStructurePiece(
                generationContext.structureTemplateManager(),
                gateWayPoolElement,
                gatewayBlockPos,
                0,
                Rotation.NONE,
                BoundingBox.infinite(),
                LiquidSettings.APPLY_WATERLOGGING
        ));

        for(int i = 0; i < pieceCount; i++) {
            StructurePoolElement poolElement = ruin_pillar_template_pool.value().getRandomTemplate(generationContext.random());

            BlockPos placeBlockPos = null;

            for(int attempt = 0; attempt < 5; attempt++) {
                BlockPos startBlockPos = generationContext.chunkPos().getBlockAt(generationContext.random().nextInt(-30, 30), 0, generationContext.random().nextInt(-30, 30));

                BlockPos groundBlockPos = new BlockPos(
                        startBlockPos.getX(),
                        generationContext.chunkGenerator().getFirstFreeHeight(startBlockPos.getX(), startBlockPos.getZ(), Heightmap.Types.OCEAN_FLOOR_WG, generationContext.heightAccessor(), generationContext.randomState()) - 2,
                        startBlockPos.getZ()
                );

                BoundingBox boundingBox = poolElement.getBoundingBox(generationContext.structureTemplateManager(), groundBlockPos, Rotation.NONE);

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
                    Rotation.NONE,
                    BoundingBox.infinite(),
                    LiquidSettings.APPLY_WATERLOGGING
            ));
        }
    }
}
