package com.teamabode.rodsnreels.common.feature;

import com.teamabode.rodsnreels.RodsNReels;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BrushableBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class RuinFeature extends Feature<NoneFeatureConfiguration> {
    public RuinFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();

        RodsNReels.LOGGER.info(String.valueOf(pos));

        level.setBlock(pos, Blocks.GLOWSTONE.defaultBlockState(), 2);

        return true;
    }
}
