package com.teamabode.rodsnreels.datagen;

import com.teamabode.rodsnreels.datagen.client.RNRModelProvider;
import com.teamabode.rodsnreels.datagen.server.RNRBiomeTagsProvider;
import com.teamabode.rodsnreels.datagen.server.RNRItemTagsProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class RodsNReelsData implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();

        // Server Data
        pack.addProvider(RNRBiomeTagsProvider::new);
        pack.addProvider(RNRItemTagsProvider::new);

        // Client Data
        pack.addProvider(RNRModelProvider::new);
    }
}
