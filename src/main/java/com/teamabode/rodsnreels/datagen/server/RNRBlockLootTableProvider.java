package com.teamabode.rodsnreels.datagen.server;

import com.teamabode.rodsnreels.core.registry.RNRItems;
import com.teamabode.rodsnreels.core.registry.RNRLootTables;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.AnyOfLootCondition;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.condition.LocationCheckLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantedCountIncreaseLootFunction;
import net.minecraft.loot.function.FurnaceSmeltLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.EntityEquipmentPredicate;
import net.minecraft.predicate.entity.EntityFlagsPredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.EnchantmentsPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.predicate.item.ItemSubPredicateTypes;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RNRBlockLootTableProvider extends FabricBlockLootTableProvider {
    public RNRBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    private AnyOfLootCondition.Builder shouldSmeltLoot() {
        RegistryWrapper.Impl<Enchantment> registryLookup = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return AnyOfLootCondition.builder(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, EntityPredicate.Builder.create().flags(EntityFlagsPredicate.Builder.create().onFire(true))), EntityPropertiesLootCondition.builder(LootContext.EntityTarget.DIRECT_ATTACKER, EntityPredicate.Builder.create().equipment(EntityEquipmentPredicate.Builder.create().mainhand(ItemPredicate.Builder.create().subPredicate(ItemSubPredicateTypes.ENCHANTMENTS, EnchantmentsPredicate.enchantments(List.of(new EnchantmentPredicate(registryLookup.getOrThrow(EnchantmentTags.SMELTS_LOOT), NumberRange.IntRange.ANY))))))));
    }

    @Override
    public void generate() {
        RegistryWrapper.Impl<Biome> registryLookup = this.registryLookup.getWrapperOrThrow(RegistryKeys.BIOME);
        this.lootTables.put(RNRLootTables.FISHING_JUNK, LootTable.builder().pool(LootPool.builder().with(ItemEntry.builder(Items.SWEET_BERRIES).conditionally(LocationCheckLootCondition.builder(LocationPredicate.Builder.create().biome(RegistryEntryList.of(registryLookup.getOrThrow(BiomeKeys.OLD_GROWTH_PINE_TAIGA), registryLookup.getOrThrow(BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA), registryLookup.getOrThrow(BiomeKeys.TAIGA), registryLookup.getOrThrow(BiomeKeys.SNOWY_TAIGA)))))).with(ItemEntry.builder(Items.GLOW_INK_SAC).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(10.0F))).conditionally(LocationCheckLootCondition.builder(LocationPredicate.Builder.createY(NumberRange.DoubleRange.atMost(30.0))))).with(ItemEntry.builder(Items.GLOW_BERRIES).conditionally(LocationCheckLootCondition.builder(LocationPredicate.Builder.createY(NumberRange.DoubleRange.atMost(30.0)))))));
        this.lootTables.put(RNRLootTables.ENTITIES_SQUID_MEAT, LootTable.builder().pool(LootPool.builder().with(ItemEntry.builder(RNRItems.SQUID).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))).apply(FurnaceSmeltLootFunction.builder().conditionally(this.shouldSmeltLoot())).apply(EnchantedCountIncreaseLootFunction.builder(this.registryLookup, UniformLootNumberProvider.create(0.0F, 1.0F))))));
    }
}
