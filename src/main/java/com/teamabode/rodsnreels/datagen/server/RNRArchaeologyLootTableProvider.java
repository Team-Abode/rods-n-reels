package com.teamabode.rodsnreels.datagen.server;

import com.teamabode.rodsnreels.core.registry.RNRItems;
import com.teamabode.rodsnreels.core.registry.RNRLootTables;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class RNRArchaeologyLootTableProvider extends SimpleFabricLootTableProvider {

    public RNRArchaeologyLootTableProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup, LootContextTypes.ARCHAEOLOGY);
    }

    @Override
    public void accept(BiConsumer<RegistryKey<LootTable>, LootTable.Builder> exporter) {
        exporter.accept(RNRLootTables.RIVER_ARCHAEOLOGY, createRiverArchaeology());
    }

    private static LootTable.Builder createRiverArchaeology() {
        var lootTable = LootTable.builder();
        var pool = LootPool.builder();

        pool.with(ItemEntry.builder(Items.STICK));
        pool.with(ItemEntry.builder(Items.COAL));
        pool.with(ItemEntry.builder(RNRItems.GILLS_POTTERY_SHERD));
        pool.with(ItemEntry.builder(Items.GLASS_PANE));
        pool.with(ItemEntry.builder(Items.BONE));
        pool.with(ItemEntry.builder(Items.WHEAT_SEEDS));
        pool.with(ItemEntry.builder(Items.IRON_NUGGET));

        lootTable.pool(pool);
        return lootTable;
    }
}
