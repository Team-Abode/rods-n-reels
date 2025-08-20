package com.teamabode.rodsnreels.datagen.server.tags;

import com.teamabode.rodsnreels.core.registry.RNREnchantments;
import com.teamabode.rodsnreels.core.tag.RNREnchantmentTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.EnchantmentTags;

import java.util.concurrent.CompletableFuture;

public class RNREnchantmentTagProvider extends FabricTagProvider.EnchantmentTagProvider {

    public RNREnchantmentTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup lookup) {
        this.getOrCreateTagBuilder(RNREnchantmentTags.FISHING_EXCLUSIVE_SET)
                .add(Enchantments.LUCK_OF_THE_SEA).add(RNREnchantments.REELING);

        this.getOrCreateTagBuilder(RNREnchantmentTags.GALVANIZE_EXCLUSIVE_SET)
                .add(Enchantments.CHANNELING);

        this.getOrCreateTagBuilder(EnchantmentTags.RIPTIDE_EXCLUSIVE_SET)
                .add(RNREnchantments.GALVANIZE);

        this.getOrCreateTagBuilder(EnchantmentTags.NON_TREASURE)
                .add(RNREnchantments.REELING);

        this.getOrCreateTagBuilder(EnchantmentTags.TREASURE)
                .add(RNREnchantments.GALVANIZE);
    }
}
