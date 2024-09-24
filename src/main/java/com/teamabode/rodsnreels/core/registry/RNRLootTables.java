package com.teamabode.rodsnreels.core.registry;

import com.teamabode.rodsnreels.RodsNReels;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class RNRLootTables {
    public static final RegistryKey<LootTable> RIVER_ARCHAEOLOGY = createKey("archaeology/river");

    public static final RegistryKey<LootTable> FISHING_JUNK_GAMEPLAY = createKey("gameplay/fishing/junk");
    public static final RegistryKey<LootTable> SQUID_MEAT_ENTITIES = createKey("entities/squid_meat");

    public static final RegistryKey<LootTable> GOLDFISH_ENTITIES = createKey("entities/goldfish");

    private static RegistryKey<LootTable> createKey(String string) {
        return RegistryKey.of(RegistryKeys.LOOT_TABLE, RodsNReels.id(string));
    }
}
