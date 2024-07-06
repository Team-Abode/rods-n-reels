package com.teamabode.rodsnreels.common.event;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.NumberProviders;

public class RNRLootTableEvents {

    public static void modifyVanillaLootTables() {
        LootTableEvents.MODIFY.register((id, builder, source) -> {
            if (EntityType.SQUID.getDefaultLootTable().equals(id)) {

            }
        });
    }
}
