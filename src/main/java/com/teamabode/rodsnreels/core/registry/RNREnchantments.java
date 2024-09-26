package com.teamabode.rodsnreels.core.registry;

import com.teamabode.rodsnreels.RodsNReels;
import com.teamabode.rodsnreels.core.tag.RNREnchantmentTags;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.EnchantmentEffectEntry;
import net.minecraft.enchantment.effect.value.AddEnchantmentEffect;
import net.minecraft.item.Item;
import net.minecraft.registry.*;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.text.Text;

import java.util.List;
import java.util.Optional;

public class RNREnchantments {
    public static final RegistryKey<Enchantment> GALVANIZE = createKey("galvanize");

    public static void register(Registerable<Enchantment> registry) {
        var items = registry.getRegistryLookup(RegistryKeys.ITEM);
        var enchantments = registry.getRegistryLookup(RegistryKeys.ENCHANTMENT);

        registry.register(GALVANIZE, createGalvanize(items, enchantments));
    }

    private static Enchantment createGalvanize(RegistryEntryLookup<Item> items, RegistryEntryLookup<Enchantment> enchantments) {
        var tridentEnchantable = items.getOrThrow(ItemTags.TRIDENT_ENCHANTABLE);
        var galvanizeExclusiveSet = enchantments.getOrThrow(RNREnchantmentTags.GALVANIZE_EXCLUSIVE_SET);
        var levelBasedValue = new EnchantmentLevelBasedValue.Linear(1.0f, 1.0f);

        Enchantment.Definition definition = new Enchantment.Definition(
                tridentEnchantable,
                Optional.empty(),
                1, 2,
                new Enchantment.Cost(25, 10),
                new Enchantment.Cost(50, 0),
                8,
                List.of(AttributeModifierSlot.HAND)
        );
        ComponentMap.Builder components = ComponentMap.builder()
                .add(RNREnchantmentEffectComponentTypes.TRIDENT_MAX_GALVANIZE_TARGETS, List.of(
                        new EnchantmentEffectEntry<>(new AddEnchantmentEffect(levelBasedValue), Optional.empty())
                ));

        return new Enchantment(
                Text.translatable("enchantment.rods_n_reels.galvanize"),
                definition,
                galvanizeExclusiveSet,
                components.build()
        );
    }

    private static RegistryKey<Enchantment> createKey(String name) {
        return RegistryKey.of(RegistryKeys.ENCHANTMENT, RodsNReels.id(name));
    }
}
