package com.teamabode.rodsnreels.datagen.server;

import com.teamabode.rodsnreels.core.tag.RNRBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class RNRBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public RNRBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup provider) {
        bubbledewGrowsOn();
    }

    private void bubbledewGrowsOn() {
        this.getOrCreateTagBuilder(RNRBlockTags.BUBBLEDEW_GROWS_ON)
                .add(Blocks.GRAVEL, Blocks.SAND)
                .forceAddTag(BlockTags.BASE_STONE_OVERWORLD);
    }
}
