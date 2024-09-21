package com.teamabode.rodsnreels.common.feature;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BrushableBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class SuspiciousBlockFeature extends Feature<SuspiciousBlockConfig> {
    public SuspiciousBlockFeature() {
        super(SuspiciousBlockConfig.CODEC);
    }

    @Override
    public boolean generate(FeatureContext<SuspiciousBlockConfig> context) {
        StructureWorldAccess level = context.getWorld();
        SuspiciousBlockConfig config = context.getConfig();
        Random random = context.getRandom();
        BlockPos pos = context.getOrigin();
        BlockState state = config.toPlace().get(random, pos);

        if (state.canPlaceAt(level, pos) && level.setBlockState(pos, state, 2)) {
            if (level.getBlockEntity(pos) instanceof BrushableBlockEntity blockEntity) {
                blockEntity.setLootTable(config.lootTable(), pos.asLong());
            }
            return true;
        }
        return false;
    }
}
