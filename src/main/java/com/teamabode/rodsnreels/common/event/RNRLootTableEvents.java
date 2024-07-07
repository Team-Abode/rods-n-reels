package com.teamabode.rodsnreels.common.event;

import com.teamabode.rodsnreels.core.registry.RNRItems;
import com.teamabode.rodsnreels.core.registry.RNRLootTables;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;

public class RNRLootTableEvents {
    public static void modifyVanillaLootTables() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source) -> {
            if (key.equals(BuiltInLootTables.FISHING_FISH)) {
                tableBuilder.modifyPools(builder -> builder.add(LootItem.lootTableItem(RNRItems.SQUID).setWeight(15)));
            }
            if (key.equals(EntityType.SQUID.getDefaultLootTable()) || key.equals(EntityType.GLOW_SQUID.getDefaultLootTable())) {
                tableBuilder.withPool(LootPool.lootPool().add(NestedLootTable.lootTableReference(RNRLootTables.SQUID_MEAT)));
            }
        });
    }
}
