package com.teamabode.rodsnreels.datagen.server;

import com.teamabode.rodsnreels.core.registry.RNRItems;
import com.teamabode.rodsnreels.core.registry.RNRLootTables;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.predicates.AnyOfCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RNRLootTablesProvider extends FabricBlockLootTableProvider {
    public RNRLootTablesProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    private AnyOfCondition.Builder shouldSmeltLoot() {
        HolderLookup.RegistryLookup<Enchantment> registryLookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return AnyOfCondition.anyOf(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true))), LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.DIRECT_ATTACKER, EntityPredicate.Builder.entity().equipment(EntityEquipmentPredicate.Builder.equipment().mainhand(ItemPredicate.Builder.item().withSubPredicate(ItemSubPredicates.ENCHANTMENTS, ItemEnchantmentsPredicate.enchantments(List.of(new EnchantmentPredicate(registryLookup.getOrThrow(EnchantmentTags.SMELTS_LOOT), MinMaxBounds.Ints.ANY))))))));
    }

    @Override
    public void generate() {
        this.map.put(RNRLootTables.SQUID_MEAT, LootTable.lootTable().withPool(LootPool.lootPool().add(((LootItem.lootTableItem(RNRItems.SQUID).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))).apply((SmeltItemFunction.smelted().when(this.shouldSmeltLoot())))).apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F))))));
    }
}
