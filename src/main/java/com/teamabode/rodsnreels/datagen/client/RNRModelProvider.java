package com.teamabode.rodsnreels.datagen.client;

import com.teamabode.rodsnreels.core.registry.RNRItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;

public class RNRModelProvider extends FabricModelProvider {
    public RNRModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators generator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerators generator) {
        generator.generateFlatItem(RNRItems.GILLS_POTTERY_SHERD, ModelTemplates.FLAT_ITEM);

        generator.generateFlatItem(RNRItems.SQUID, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(RNRItems.COOKED_SQUID, ModelTemplates.FLAT_ITEM);

        generator.generateFlatItem(RNRItems.GOLDFISH, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(RNRItems.GOLDFISH_BUCKET, ModelTemplates.FLAT_ITEM);
    }
}
