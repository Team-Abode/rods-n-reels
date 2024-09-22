package com.teamabode.rodsnreels.datagen;

import com.teamabode.rodsnreels.datagen.client.RNRModelProvider;
import com.teamabode.rodsnreels.datagen.server.*;
import com.teamabode.rodsnreels.datagen.server.tags.RNRBlockTagProvider;
import com.teamabode.rodsnreels.datagen.server.tags.RNREntityTypeTagProvider;
import com.teamabode.rodsnreels.datagen.server.tags.RNRItemTagProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class RodsNReelsDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();

        // Server Data
        pack.addProvider(RNRBlockLootTableProvider::new);
        pack.addProvider(RNREntityLootTableProvider::new);
        pack.addProvider(RNRRecipeProvider::new);

        // Tag Providers
        pack.addProvider(RNRItemTagProvider::new);
        pack.addProvider(RNRBlockTagProvider::new);
        pack.addProvider(RNREntityTypeTagProvider::new);

        // Client Data
        pack.addProvider(RNRModelProvider::new);
    }
}
