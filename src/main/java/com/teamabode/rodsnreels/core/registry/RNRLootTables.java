package com.teamabode.rodsnreels.core.registry;

import com.teamabode.rodsnreels.RodsNReels;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

public class RNRLootTables {
    public static final ResourceKey<LootTable> FISHING_JUNK = register("gameplay/fishing/junk");
    public static final ResourceKey<LootTable> SQUID_MEAT = register("entities/squid_meat");

    public static final ResourceKey<LootTable> ENTITIES_GOLDFISH = register("entities/goldfish");

    private static ResourceKey<LootTable> register(String string) {
        return ResourceKey.create(Registries.LOOT_TABLE, RodsNReels.id(string));
    }
}
