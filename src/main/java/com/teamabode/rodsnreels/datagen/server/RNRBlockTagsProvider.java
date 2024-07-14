package com.teamabode.rodsnreels.datagen.server;

import com.teamabode.rodsnreels.RodsNReels;
import com.teamabode.rodsnreels.core.registry.RNRItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class RNRBlockTagsProvider extends FabricTagProvider.BlockTagProvider {
    public static final TagKey<Block> BUBBLEDEW_GROWABLE = TagKey.create(Registries.BLOCK, RodsNReels.id("bubbledew_growable"));

    public RNRBlockTagsProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.getOrCreateTagBuilder(BUBBLEDEW_GROWABLE).add(Blocks.GRAVEL, Blocks.SAND).forceAddTag(BlockTags.BASE_STONE_OVERWORLD);
    }
}
