package com.teamabode.rodsnreels.core.registry;

import com.teamabode.rodsnreels.RodsNReels;
import com.teamabode.rodsnreels.common.block.BubbledewBlock;
import com.teamabode.rodsnreels.common.block.BubbledewStemPlantBlock;
import net.minecraft.block.AbstractBlock.Settings;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import com.teamabode.rodsnreels.common.block.BubbledewStemBlock;

public class RNRBlocks {
    public static final Block BUBBLEDEW = register(
            "bubbledew",
            new BubbledewBlock(Settings.create()
                    .mapColor(MapColor.BRIGHT_TEAL)
                    .instrument(NoteBlockInstrument.DIDGERIDOO)
                    .strength(0.25f)
                    .sounds(BlockSoundGroup.WOOD)
                    .pistonBehavior(PistonBehavior.DESTROY)
                    .luminance(state -> 15)
                    .nonOpaque()
    ));
    public static final Block BUBBLEDEW_STEM_PLANT = registerWithoutItem(
            "bubbledew_stem_plant",
            new BubbledewStemPlantBlock(Settings.create()
                    .noCollision()
                    .ticksRandomly()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.WET_GRASS)
                    .pistonBehavior(PistonBehavior.DESTROY)
    ));
    public static final Block BUBBLEDEW_STEM = registerWithoutItem(
            "bubbledew_stem",
            new BubbledewStemBlock(Settings.copy(BUBBLEDEW_STEM_PLANT))
    );

    public static void register() {

    }

    private static Block register(String name, Block block) {
        var registry = Registry.register(Registries.BLOCK, RodsNReels.id(name), block);
        Registry.register(Registries.ITEM, RodsNReels.id(name), new BlockItem(registry, new Item.Settings()));
        return registry;
    }

    private static Block registerWithoutItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, RodsNReels.id(name), block);
    }
}
