package com.teamabode.rodsnreels.datagen;

import com.teamabode.rodsnreels.core.registry.RNRBiomes;
import com.teamabode.rodsnreels.core.registry.RNRConfiguredFeatures;
import com.teamabode.rodsnreels.core.registry.RNREnchantments;
import com.teamabode.rodsnreels.core.registry.RNRPlacedFeatures;
import com.teamabode.rodsnreels.datagen.client.RNRModelProvider;
import com.teamabode.rodsnreels.datagen.server.*;
import com.teamabode.rodsnreels.datagen.server.tags.*;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class RodsNReelsDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();

        // Server Data
        pack.addProvider(RNRBlockLootTableProvider::new);
        pack.addProvider(RNREntityLootTableProvider::new);
        pack.addProvider(RNRFishingLootTableProvider::new);
        pack.addProvider(RNRArchaeologyLootTableProvider::new);
        pack.addProvider(RNRRecipeProvider::new);
        pack.addProvider(RNRItemTagProvider::new);
        pack.addProvider(RNRBlockTagProvider::new);
        pack.addProvider(RNRBiomeTagProvider::new);
        pack.addProvider(RNREntityTypeTagProvider::new);
        pack.addProvider(RNREnchantmentTagProvider::new);
        pack.addProvider(RNRDynamicRegistryProvider::new);

        // Client Data
        pack.addProvider(RNRModelProvider::new);
    }

    @Override
    public void buildRegistry(RegistryBuilder builder) {
        builder.addRegistry(RegistryKeys.ENCHANTMENT, RNREnchantments::register);
        builder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, RNRConfiguredFeatures::register);
        builder.addRegistry(RegistryKeys.PLACED_FEATURE, RNRPlacedFeatures::register);
        builder.addRegistry(RegistryKeys.BIOME, RNRBiomes::register);
    }
}
