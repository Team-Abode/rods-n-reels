package com.teamabode.rodsnreels.core.registry;

import com.teamabode.rodsnreels.RodsNReels;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class RNRLootTables {
    public static final RegistryKey<LootTable> GAMEPLAY_FISHIHG_JUNK = createKey("gameplay/fishing/junk");
    public static final RegistryKey<LootTable> ENTITIES_SQUID_MEAT = createKey("entities/squid_meat");

    public static final RegistryKey<LootTable> ENTITIES_GOLDFISH = createKey("entities/goldfish");

    private static RegistryKey<LootTable> createKey(String string) {
        return RegistryKey.of(RegistryKeys.LOOT_TABLE, RodsNReels.id(string));
    }
}
