package com.teamabode.rodsnreels.datagen.server;

import com.teamabode.rodsnreels.core.registry.RNRLootTables;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.LocationCheckLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

import java.util.concurrent.CompletableFuture;

public class RNRBlockLootTableProvider extends FabricBlockLootTableProvider {
    public RNRBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        RegistryWrapper.Impl<Biome> registryLookup = this.registryLookup.getWrapperOrThrow(RegistryKeys.BIOME);
        this.lootTables.put(RNRLootTables.GAMEPLAY_FISHIHG_JUNK, LootTable.builder().pool(LootPool.builder().with(ItemEntry.builder(Items.SWEET_BERRIES).conditionally(LocationCheckLootCondition.builder(LocationPredicate.Builder.create().biome(RegistryEntryList.of(registryLookup.getOrThrow(BiomeKeys.OLD_GROWTH_PINE_TAIGA), registryLookup.getOrThrow(BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA), registryLookup.getOrThrow(BiomeKeys.TAIGA), registryLookup.getOrThrow(BiomeKeys.SNOWY_TAIGA)))))).with(ItemEntry.builder(Items.GLOW_INK_SAC).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(10.0F))).conditionally(LocationCheckLootCondition.builder(LocationPredicate.Builder.createY(NumberRange.DoubleRange.atMost(30.0))))).with(ItemEntry.builder(Items.GLOW_BERRIES).conditionally(LocationCheckLootCondition.builder(LocationPredicate.Builder.createY(NumberRange.DoubleRange.atMost(30.0)))))));
    }
}
