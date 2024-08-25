package com.teamabode.rodsnreels.core.registry;

import com.teamabode.rodsnreels.RodsNReels;
import com.teamabode.rodsnreels.common.block.BubbledewBlock;
import com.teamabode.rodsnreels.common.block.BubbledewStemPlantBlock;
import com.teamabode.rodsnreels.common.block.BubbledewStemBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class RNRBlocks {
    public static final Block BUBBLEDEW = register(
            "bubbledew",
            new BubbledewBlock(Properties.of()
                    .mapColor(MapColor.WARPED_WART_BLOCK)
                    .instrument(NoteBlockInstrument.DIDGERIDOO)
                    .strength(1.0f)
                    .sound(SoundType.WOOD)
                    .pushReaction(PushReaction.DESTROY)
                    .lightLevel(state -> 15)
    ));
    public static final Block BUBBLEDEW_STEM_PLANT = register(
            "bubbledew_stem_plant",
            new BubbledewStemPlantBlock(Properties.of()
                    .noCollission()
                    .randomTicks()
                    .instabreak()
                    .sound(SoundType.WET_GRASS)
                    .pushReaction(PushReaction.DESTROY)
    ));
    public static final Block BUBBLEDEW_STEM = register(
            "bubbledew_stem",
            new BubbledewStemBlock(Properties.ofFullCopy(BUBBLEDEW_STEM_PLANT))
    );

    public static void register() {

    }

    private static Block register(String name, Block block) {
        var registry = Registry.register(BuiltInRegistries.BLOCK, RodsNReels.id(name), block);
        Registry.register(BuiltInRegistries.ITEM, RodsNReels.id(name), new BlockItem(registry, new Item.Properties()));
        return registry;
    }
}
