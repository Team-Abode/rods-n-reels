package com.teamabode.rodsnreels.common.block;

import com.mojang.serialization.MapCodec;
import com.teamabode.rodsnreels.core.registry.RNRBlocks;
import net.minecraft.block.AbstractPlantStemBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidFillable;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class BubbledewStemBlock extends AbstractPlantStemBlock implements FluidFillable {
    public static final MapCodec<BubbledewStemBlock> CODEC = BubbledewStemBlock.createCodec(BubbledewStemBlock::new);
    public static final BooleanProperty ATTACHED = Properties.ATTACHED;

    protected static final VoxelShape SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 9.0, 16.0);

    public BubbledewStemBlock(Settings properties) {
        super(properties, Direction.UP, SHAPE, true, 0.14);

        this.setDefaultState(this.stateManager.getDefaultState().with(ATTACHED, false).with(AGE, 0));
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState blockState, BlockView blockGetter, BlockPos blockPos, ShapeContext collisionContext) {
        return blockState.get(ATTACHED) ? VoxelShapes.fullCube() : SHAPE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ATTACHED);
        builder.add(AGE);
    }

    @Override
    protected void randomTick(BlockState blockState, ServerWorld serverLevel, BlockPos blockPos, Random randomSource) {
        if (randomSource.nextInt(1) != 0) return;

        BlockState aboveBlockstate = serverLevel.getBlockState(blockPos.up());

        if(!aboveBlockstate.isOf(Blocks.WATER)) return;

        serverLevel.setBlockState(blockPos.up(), RNRBlocks.BUBBLEDEW.getDefaultState());
        serverLevel.setBlockState(blockPos, blockState.with(ATTACHED, true));
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState blockState, Direction direction, BlockState otherState, WorldAccess levelAccessor, BlockPos blockPos, BlockPos blockPos2) {
        BlockState state = super.getStateForNeighborUpdate(blockState, direction, otherState, levelAccessor, blockPos, blockPos2);

        if(!state.isOf(blockState.getBlock())) return state;

        if(direction != Direction.UP) return state;

        if (otherState.isOf(RNRBlocks.BUBBLEDEW)) return state;

        return blockState.with(ATTACHED, false);
    }

    @Override
    protected boolean chooseStemState(BlockState blockState) {
        return blockState.isOf(Blocks.WATER);
    }

    @Override
    protected Block getPlant() {
        return RNRBlocks.BUBBLEDEW_STEM_PLANT;
    }

    @Override
    public boolean canAttachTo(BlockState blockState) {
        return !blockState.isOf(Blocks.MAGMA_BLOCK);
    }

    @Override
    protected int getGrowthLength(Random randomSource) {
        return 1;
    }

    @Override
    protected FluidState getFluidState(BlockState blockState) {
        return Fluids.WATER.getStill(false);
    }

    @Override
    public boolean canFillWithFluid(@Nullable PlayerEntity player, BlockView blockGetter, BlockPos blockPos, BlockState blockState, Fluid fluid) {
        return false;
    }

    @Override
    public boolean tryFillWithFluid(WorldAccess levelAccessor, BlockPos blockPos, BlockState blockState, FluidState fluidState) {
        return false;
    }

    @Override
    public MapCodec<BubbledewStemBlock> getCodec() {
        return CODEC;
    }
}
