package com.teamabode.rodsnreels.core.tag;

import com.teamabode.rodsnreels.RodsNReels;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class RNRBlockTags {
    public static final TagKey<Block> BUBBLEDEW_GROWS_ON = create("bubbledew_grows_on");

    private static TagKey<Block> create(String name) {
        return TagKey.of(RegistryKeys.BLOCK, RodsNReels.id(name));
    }
}
