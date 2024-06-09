package com.teamabode.rodsnreels.common.feature;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.entity.BrushableBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class SuspiciousBlockFeature extends Feature<SuspiciousBlockConfiguration> {
    public SuspiciousBlockFeature() {
        super(SuspiciousBlockConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<SuspiciousBlockConfiguration> context) {
        WorldGenLevel level = context.level();
        SuspiciousBlockConfiguration config = context.config();
        RandomSource random = context.random();
        BlockPos pos = context.origin();
        BlockState state = config.toPlace().getState(random, pos);

        if (state.canSurvive(level, pos) && level.setBlock(pos, state, 2)) {
            if (level.getBlockEntity(pos) instanceof BrushableBlockEntity blockEntity) {
                blockEntity.setLootTable(config.lootTable(), pos.asLong());
            }
            return true;
        }
        return false;
    }
}
