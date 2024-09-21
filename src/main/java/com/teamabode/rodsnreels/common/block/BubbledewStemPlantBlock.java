package com.teamabode.rodsnreels.common.block;

import com.mojang.serialization.MapCodec;
import com.teamabode.rodsnreels.core.registry.RNRBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AbstractPlantBlock;
import net.minecraft.block.AbstractPlantStemBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidFillable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class BubbledewStemPlantBlock extends AbstractPlantBlock implements FluidFillable {
    public static final MapCodec<BubbledewStemPlantBlock> CODEC = createCodec(BubbledewStemPlantBlock::new);

    public BubbledewStemPlantBlock(AbstractBlock.Settings properties) {
        super(properties, Direction.UP, VoxelShapes.fullCube(), true);
    }

    @Override
    public boolean canAttachTo(BlockState state) {
        return ((BubbledewStemBlock) this.getStem()).canAttachTo(state);
    }

    @Override
    protected FluidState getFluidState(BlockState blockState) {
        return Fluids.WATER.getStill(false);
    }

    @Override
    protected AbstractPlantStemBlock getStem() {
        return (AbstractPlantStemBlock) RNRBlocks.BUBBLEDEW_STEM;
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
    public MapCodec<BubbledewStemPlantBlock> getCodec() {
        return CODEC;
    }
}
