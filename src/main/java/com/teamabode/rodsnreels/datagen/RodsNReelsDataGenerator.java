package com.teamabode.rodsnreels.datagen;

import com.teamabode.rodsnreels.datagen.client.RNRModelProvider;
import com.teamabode.rodsnreels.datagen.server.*;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class RodsNReelsDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();

        // Server Data
        pack.addProvider(RNRItemTagProvider::new);
        pack.addProvider(RNRBlockLootTableProvider::new);
        pack.addProvider(RNREntityLootTableProvider::new);
        pack.addProvider(RNRRecipeProvider::new);
        pack.addProvider(RNRBlockTagProvider::new);

        // Client Data
        pack.addProvider(RNRModelProvider::new);
    }
}
