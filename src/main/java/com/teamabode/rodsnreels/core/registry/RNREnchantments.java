package com.teamabode.rodsnreels.core.registry;

import com.teamabode.rodsnreels.RodsNReels;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class RNREnchantments {
    public static final RegistryKey<Enchantment> GALVANIZE = createKey("galvanize");

    private static RegistryKey<Enchantment> createKey(String name) {
        return RegistryKey.of(RegistryKeys.ENCHANTMENT, RodsNReels.id(name));
    }
}
