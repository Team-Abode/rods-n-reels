package com.teamabode.rodsnreels.datagen.client;

import com.teamabode.rodsnreels.core.registry.RNRItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;

public class RNRModelProvider extends FabricModelProvider {
    public RNRModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator generator) {
        generator.register(RNRItems.GILLS_POTTERY_SHERD, Models.GENERATED);

        generator.register(RNRItems.SQUID, Models.GENERATED);
        generator.register(RNRItems.COOKED_SQUID, Models.GENERATED);

        generator.register(RNRItems.GOLDFISH, Models.GENERATED);
        generator.register(RNRItems.GOLDFISH_BUCKET, Models.GENERATED);
    }
}
