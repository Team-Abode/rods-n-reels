package com.teamabode.rodsnreels.common.block;

import com.mojang.serialization.MapCodec;
import com.teamabode.rodsnreels.core.registry.RNRBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.ATTACHED;

public class BubbledewStemBlock extends GrowingPlantHeadBlock implements LiquidBlockContainer {
    public static final MapCodec<BubbledewStemBlock> CODEC = BubbledewStemBlock.simpleCodec(BubbledewStemBlock::new);

    protected static final VoxelShape SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 9.0, 16.0);

    public BubbledewStemBlock(Properties properties) {
        super(properties, Direction.UP, SHAPE, true, 0.14);

        this.registerDefaultState(this.stateDefinition.any().setValue(ATTACHED, false).setValue(AGE, 0));
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return blockState.getValue(ATTACHED) ? Shapes.block() : SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ATTACHED);
        builder.add(AGE);
    }

    @Override
    protected void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if (randomSource.nextInt(1) != 0) return;

        BlockState aboveBlockstate = serverLevel.getBlockState(blockPos.above());

        if(!aboveBlockstate.is(Blocks.WATER)) return;

        serverLevel.setBlockAndUpdate(blockPos.above(), RNRBlocks.BUBBLEDEW.defaultBlockState());
        serverLevel.setBlockAndUpdate(blockPos, blockState.setValue(ATTACHED, true));
    }

    @Override
    protected BlockState updateShape(BlockState blockState, Direction direction, BlockState otherState, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos2) {
        BlockState state = super.updateShape(blockState, direction, otherState, levelAccessor, blockPos, blockPos2);

        if(!state.is(blockState.getBlock())) return state;

        if(direction != Direction.UP) return state;

        if (otherState.is(RNRBlocks.BUBBLEDEW)) return state;

        return blockState.setValue(ATTACHED, false);
    }

    @Override
    protected boolean canGrowInto(BlockState blockState) {
        return blockState.is(Blocks.WATER);
    }

    @Override
    protected Block getBodyBlock() {
        return RNRBlocks.BUBBLEDEW_STEM_PLANT;
    }

    @Override
    public boolean canAttachTo(BlockState blockState) {
        return !blockState.is(Blocks.MAGMA_BLOCK);
    }

    @Override
    protected int getBlocksToGrowWhenBonemealed(RandomSource randomSource) {
        return 1;
    }

    @Override
    protected FluidState getFluidState(BlockState blockState) {
        return Fluids.WATER.getSource(false);
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
    public MapCodec<BubbledewStemBlock> codec() {
        return CODEC;
    }
}
