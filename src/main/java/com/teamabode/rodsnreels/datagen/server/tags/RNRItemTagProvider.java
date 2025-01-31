package com.teamabode.rodsnreels.datagen.server.tags;

import com.teamabode.rodsnreels.core.registry.RNRItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import java.util.concurrent.CompletableFuture;

public class RNRItemTagProvider extends FabricTagProvider.ItemTagProvider {


    public RNRItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup provider) {
        appendSherds();
    }
    private void appendSherds() {
        this.getOrCreateTagBuilder(ItemTags.DECORATED_POT_SHERDS).add(RNRItems.GILLS_POTTERY_SHERD);
        this.getOrCreateTagBuilder(ConventionalItemTags.COOKED_MEATS_FOODS).add(RNRItems.COOKED_SQUID);
        this.getOrCreateTagBuilder(ConventionalItemTags.RAW_MEATS_FOODS).add(RNRItems.SQUID);
    }
}
