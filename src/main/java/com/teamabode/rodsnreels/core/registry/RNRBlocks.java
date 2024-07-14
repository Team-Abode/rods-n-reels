package com.teamabode.rodsnreels.core.registry;

import com.teamabode.rodsnreels.RodsNReels;
import com.teamabode.rodsnreels.common.block.Bubbledew;
import com.teamabode.rodsnreels.common.block.BubbledewStem;
import com.teamabode.rodsnreels.common.block.BubbledewStemTop;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class RNRBlocks {
    public static final BubbledewStem BUBBBLEDEW_STEM = new BubbledewStem(BlockBehaviour.Properties.of().noCollission().randomTicks().instabreak().sound(SoundType.WET_GRASS).pushReaction(PushReaction.DESTROY));
    public static final BubbledewStemTop BUBBBLEDEW_STEM_TOP = new BubbledewStemTop(BlockBehaviour.Properties.of().noCollission().randomTicks().instabreak().sound(SoundType.WET_GRASS).pushReaction(PushReaction.DESTROY));
    public static final Bubbledew BUBBBLEDEW = new Bubbledew(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).instrument(NoteBlockInstrument.DIDGERIDOO).strength(1.0f).sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY).lightLevel(blockState -> 15));

    public static void registerBlocks() {
        register("bubbledew_stem", BUBBBLEDEW_STEM);
        Registry.register(BuiltInRegistries.BLOCK, RodsNReels.id("bubbledew_stem_top"), BUBBBLEDEW_STEM_TOP);

        register("bubbledew", BUBBBLEDEW);
    }

    private static void register(String name, Block block) {
        var registry = Registry.register(BuiltInRegistries.BLOCK, RodsNReels.id(name), block);
        Registry.register(BuiltInRegistries.ITEM, RodsNReels.id(name), new BlockItem(registry, new Item.Properties()));
    }
}
