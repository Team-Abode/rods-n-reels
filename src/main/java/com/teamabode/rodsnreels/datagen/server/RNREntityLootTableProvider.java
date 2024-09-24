package com.teamabode.rodsnreels.datagen.server;

import com.teamabode.rodsnreels.core.registry.RNRItems;
import com.teamabode.rodsnreels.core.registry.RNRLootTables;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.AnyOfLootCondition;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextTypes;
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
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.EnchantmentsPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.predicate.item.ItemSubPredicateTypes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.EnchantmentTags;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class RNREntityLootTableProvider extends SimpleFabricLootTableProvider {
    private final RegistryWrapper.WrapperLookup registryLookup;

    public RNREntityLootTableProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup, LootContextTypes.ENTITY);
        this.registryLookup = registryLookup.join();
    }

    @Override
    public void accept(BiConsumer<RegistryKey<LootTable>, LootTable.Builder> exporter) {
        createGoldfish(exporter);
        createSquidMeat(exporter, this.registryLookup);
    }

    private static void createGoldfish(BiConsumer<RegistryKey<LootTable>, LootTable.Builder> exporter) {
        LootTable.Builder lootTable = LootTable.builder();

        lootTable.pool(LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1.0f))
                .with(ItemEntry.builder(RNRItems.GOLDFISH))
        );
        lootTable.pool(LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1.0f))
                .with(ItemEntry.builder(Items.BONE_MEAL))
                .conditionally(RandomChanceLootCondition.builder(0.05f))
        );

        exporter.accept(RNRLootTables.GOLDFISH_ENTITIES, lootTable);
    }

    private static void createSquidMeat(BiConsumer<RegistryKey<LootTable>, LootTable.Builder> exporter, RegistryWrapper.WrapperLookup registryLookup) {
        LootTable.Builder lootTable = LootTable.builder();

        var setCount = SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0f));
        var furnaceSmelt = FurnaceSmeltLootFunction.builder().conditionally(shouldSmeltLoot(registryLookup));
        var enchantedCountIncrease = EnchantedCountIncreaseLootFunction.builder(registryLookup, UniformLootNumberProvider.create(0.0f, 1.0f));

        lootTable.pool(LootPool.builder()
                .with(ItemEntry.builder(RNRItems.SQUID)
                        .apply(setCount)
                        .apply(furnaceSmelt)
                        .apply(enchantedCountIncrease)
                )
        );
        exporter.accept(RNRLootTables.SQUID_MEAT_ENTITIES, lootTable);
    }

    private static AnyOfLootCondition.Builder shouldSmeltLoot(RegistryWrapper.WrapperLookup registryLookup) {
        RegistryWrapper.Impl<Enchantment> enchantments = registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        var condition = AnyOfLootCondition.builder();
        var smeltsLoot = new EnchantmentPredicate(enchantments.getOrThrow(EnchantmentTags.SMELTS_LOOT), NumberRange.IntRange.ANY);

        var onFirePredicate = EntityPredicate.Builder.create().flags(EntityFlagsPredicate.Builder.create().onFire(true));
        var hasSmeltsLootPredicate = EntityPredicate.Builder.create().equipment(EntityEquipmentPredicate.Builder.create().mainhand(ItemPredicate.Builder.create()
                .subPredicate(
                        ItemSubPredicateTypes.ENCHANTMENTS,
                        EnchantmentsPredicate.enchantments(List.of(smeltsLoot))
                )
        ));
        condition.add(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, onFirePredicate));
        condition.add(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.DIRECT_ATTACKER, hasSmeltsLootPredicate));
        return condition;
    }

    @Override
    public String getName() {
        return "Entity Loot Tables";
    }
}
