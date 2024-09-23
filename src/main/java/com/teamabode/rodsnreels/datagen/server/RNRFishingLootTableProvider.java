package com.teamabode.rodsnreels.datagen.server;

import com.teamabode.rodsnreels.core.registry.RNRLootTables;
import com.teamabode.rodsnreels.core.tag.RNRBiomeTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.LocationCheckLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.biome.Biome;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class RNRFishingLootTableProvider extends SimpleFabricLootTableProvider {
    private final RegistryWrapper.WrapperLookup registryLookup;

    public RNRFishingLootTableProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup, LootContextTypes.FISHING);
        this.registryLookup = registryLookup.join();
    }

    @Override
    public void accept(BiConsumer<RegistryKey<LootTable>, LootTable.Builder> exporter) {
        createFishingJunk(exporter, registryLookup);
    }

    private static void createFishingJunk(BiConsumer<RegistryKey<LootTable>, LootTable.Builder> exporter, RegistryWrapper.WrapperLookup lookup) {
        var lootTable = LootTable.builder();

        var sweetBerries = ItemEntry.builder(Items.SWEET_BERRIES)
                .conditionally(checkBiomes(lookup, RNRBiomeTags.SWEET_BERRIES_HAS_FISHING_JUNK));

        var mangrovePropagule = ItemEntry.builder(Items.MANGROVE_PROPAGULE)
                .conditionally(checkBiomes(lookup, RNRBiomeTags.MANGROVE_PROPAGULE_HAS_FISHING_JUNK));

        var glowBerries = ItemEntry.builder(Items.GLOW_BERRIES)
                .conditionally(checkHeight());

        var glowInkSac = ItemEntry.builder(Items.GLOW_INK_SAC)
                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(10.0f)))
                .conditionally(checkHeight());

        lootTable.pool(LootPool.builder()
                .with(sweetBerries)
                .with(mangrovePropagule)
                .with(glowBerries)
                .with(glowInkSac)
        );
        exporter.accept(RNRLootTables.GAMEPLAY_FISHIHG_JUNK, lootTable);
    }

    private static LootCondition.Builder checkBiomes(RegistryWrapper.WrapperLookup lookup, TagKey<Biome> biomeTag) {
        var biomes = lookup.getWrapperOrThrow(RegistryKeys.BIOME);
        return LocationCheckLootCondition.builder(LocationPredicate.Builder.create().biome(biomes.getOrThrow(biomeTag)));
    }

    private static LootCondition.Builder checkHeight() {
        return LocationCheckLootCondition.builder(LocationPredicate.Builder.createY(NumberRange.DoubleRange.atMost(30.0)));
    }

    @Override
    public String getName() {
        return "Fishing Loot Tables";
    }
}
