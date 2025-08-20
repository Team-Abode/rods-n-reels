package com.teamabode.rodsnreels.common.loot;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamabode.rodsnreels.core.registry.RNREnchantments;
import com.teamabode.rodsnreels.core.registry.RNRLootFunctionTypes;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameter;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.function.ConditionalLootFunction;
import net.minecraft.loot.function.LootFunction;
import net.minecraft.loot.function.LootFunctionType;
import net.minecraft.loot.provider.number.LootNumberProvider;
import net.minecraft.loot.provider.number.LootNumberProviderTypes;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;

import java.util.List;
import java.util.Set;

public class EnchantedToolCountIncreaseLootFunction extends ConditionalLootFunction {
    public static final MapCodec<EnchantedToolCountIncreaseLootFunction> CODEC = RecordCodecBuilder.mapCodec((instance) -> addConditionsField(instance).and(instance.group(Enchantment.ENTRY_CODEC.fieldOf("enchantment").forGetter((function) -> function.enchantment), LootNumberProviderTypes.CODEC.fieldOf("count").forGetter((function) -> function.count), Codec.INT.optionalFieldOf("limit", 0).forGetter((function) -> function.limit))).apply(instance, EnchantedToolCountIncreaseLootFunction::new));
    private final RegistryEntry<Enchantment> enchantment;
    private final LootNumberProvider count;
    private final int limit;

    private EnchantedToolCountIncreaseLootFunction(List<LootCondition> conditions, RegistryEntry<Enchantment> enchantment, LootNumberProvider count, int limit) {
        super(conditions);
        this.enchantment = enchantment;
        this.count = count;
        this.limit = limit;
    }

    public static Builder builder(RegistryWrapper.WrapperLookup registries, LootNumberProvider count) {
        RegistryWrapper.Impl<Enchantment> enchantments = registries.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return new Builder(enchantments.getOrThrow(RNREnchantments.REELING), count);
    }

    @Override
    public LootFunctionType<EnchantedToolCountIncreaseLootFunction> getType() {
        return RNRLootFunctionTypes.ENCHANTED_TOOL_COUNT_INCREASE;
    }

    @Override
    public Set<LootContextParameter<?>> getRequiredParameters() {
        return Sets.union(ImmutableSet.of(LootContextParameters.TOOL), this.count.getRequiredParameters());
    }

    private boolean hasLimit() {
        return this.limit > 0;
    }

    @Override
    public ItemStack process(ItemStack stack, LootContext context) {
        ItemStack tool = context.get(LootContextParameters.TOOL);
        if (tool != null) {
            int level = EnchantmentHelper.getLevel(this.enchantment, tool);
            if (level == 0) {
                return stack;
            }
            float amount = level * this.count.nextFloat(context);
            stack.increment(Math.round(amount));
            if (this.hasLimit()) {
                stack.capCount(this.limit);
            }
        }
        return stack;
    }

    public static class Builder extends ConditionalLootFunction.Builder<Builder> {
        private final RegistryEntry<Enchantment> enchantment;
        private final LootNumberProvider count;
        private int limit;

        public Builder(RegistryEntry<Enchantment> enchantment, LootNumberProvider count) {
            this.enchantment = enchantment;
            this.count = count;
        }

        @Override
        protected Builder getThisBuilder() {
            return this;
        }

        public Builder withLimit(int limit) {
            this.limit = limit;
            return this;
        }

        @Override
        public LootFunction build() {
            return new EnchantedToolCountIncreaseLootFunction(this.getConditions(), this.enchantment, this.count, this.limit);
        }
    }
}
