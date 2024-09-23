package com.teamabode.rodsnreels.core.misc;

import com.teamabode.rodsnreels.core.registry.RNRItems;
import com.teamabode.rodsnreels.core.registry.RNRLootTables;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootTableEntry;

public class RNRLootTableEvents {
    public static void modifyVanillaLootTables() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (key.equals(LootTables.FISHING_FISH_GAMEPLAY)) {
                tableBuilder.pool(LootPool.builder().with(ItemEntry.builder(RNRItems.SQUID).weight(15)));
            }
            if (key.equals(LootTables.FISHING_JUNK_GAMEPLAY)) {
                tableBuilder.modifyPools(builder -> builder.with(LootTableEntry.builder(RNRLootTables.GAMEPLAY_FISHIHG_JUNK).weight(30)));
            }
            if (key.equals(EntityType.SQUID.getLootTableId()) || key.equals(EntityType.GLOW_SQUID.getLootTableId())) {
                tableBuilder.pool(LootPool.builder().with(LootTableEntry.builder(RNRLootTables.ENTITIES_SQUID_MEAT)));
            }
        });
    }
}
