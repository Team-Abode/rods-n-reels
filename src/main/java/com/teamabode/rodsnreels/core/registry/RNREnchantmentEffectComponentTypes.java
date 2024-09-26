package com.teamabode.rodsnreels.core.registry;

import com.teamabode.rodsnreels.RodsNReels;
import net.minecraft.component.ComponentType;
import net.minecraft.enchantment.effect.EnchantmentEffectEntry;
import net.minecraft.enchantment.effect.EnchantmentValueEffect;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.List;
import java.util.function.UnaryOperator;

public class RNREnchantmentEffectComponentTypes {
    public static final ComponentType<List<EnchantmentEffectEntry<EnchantmentValueEffect>>> TRIDENT_MAX_GALVANIZE_TARGETS = register("trident_max_galvanize_targets", builder -> builder.codec(
            EnchantmentEffectEntry.createCodec(EnchantmentValueEffect.CODEC, LootContextTypes.ENCHANTED_ENTITY).listOf()
    ));

    public static void register() {}

    private static <T> ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builder) {
        return Registry.register(Registries.ENCHANTMENT_EFFECT_COMPONENT_TYPE, RodsNReels.id(name), builder.apply(ComponentType.builder()).build());
    }
}
