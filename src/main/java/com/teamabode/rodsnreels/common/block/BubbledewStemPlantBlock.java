package com.teamabode.rodsnreels.common.block;

import com.mojang.serialization.MapCodec;
import com.teamabode.rodsnreels.core.registry.RNRBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.Shapes;
import org.jetbrains.annotations.Nullable;

public class BubbledewStemPlantBlock extends GrowingPlantBodyBlock implements LiquidBlockContainer {
    public static final MapCodec<BubbledewStemPlantBlock> CODEC = simpleCodec(BubbledewStemPlantBlock::new);

    public BubbledewStemPlantBlock(BlockBehaviour.Properties properties) {
        super(properties, Direction.UP, Shapes.block(), true);
    }

    @Override
    public boolean canAttachTo(BlockState blockState) {
        return this.getHeadBlock().canAttachTo(blockState);
    }

    @Override
    protected FluidState getFluidState(BlockState blockState) {
        return Fluids.WATER.getSource(false);
    }

    @Override
    protected GrowingPlantHeadBlock getHeadBlock() {
        return (GrowingPlantHeadBlock) RNRBlocks.BUBBLEDEW_STEM;
    }

    @Override
    public boolean canPlaceLiquid(@Nullable Player player, BlockGetter blockGetter, BlockPos blockPos, BlockState blockState, Fluid fluid) {
        return false;
    }

    @Override
    public boolean placeLiquid(LevelAccessor levelAccessor, BlockPos blockPos, BlockState blockState, FluidState fluidState) {
        return false;
    }

    @Override
    public MapCodec<BubbledewStemPlantBlock> codec() {
        return CODEC;
    }
}
