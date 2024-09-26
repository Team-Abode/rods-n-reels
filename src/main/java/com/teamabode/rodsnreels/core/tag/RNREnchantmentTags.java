package com.teamabode.rodsnreels.core.tag;

import com.teamabode.rodsnreels.RodsNReels;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class RNREnchantmentTags {
    public static final TagKey<Enchantment> GALVANIZE_EXCLUSIVE_SET = create("exclusive_set/galvanize");

    private static TagKey<Enchantment> create(String name) {
        return TagKey.of(RegistryKeys.ENCHANTMENT, RodsNReels.id(name));
    }
}
