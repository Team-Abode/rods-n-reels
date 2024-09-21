package com.teamabode.rodsnreels;

import com.teamabode.rodsnreels.core.misc.RNRLootTableEvents;
import com.teamabode.rodsnreels.core.registry.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RodsNReels implements ModInitializer {
    public static final String MOD_ID = "rods_n_reels";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public void onInitialize() {
        RNRItems.register();
        RNRBlocks.register();
        RNREntityTypes.register();
        RNRFeatures.register();
        RNRDecoratedPotPatterns.register();
        RNRStructures.register();
        RNRSoundEvents.register();

        RNRPlacedFeatures.createBiomeModifications();
        RNRLootTableEvents.modifyVanillaLootTables();
        registerItemGroupEvents();
    }

    public static ResourceLocation id(String name) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
    }

    public static void registerItemGroupEvents() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FOOD_AND_DRINKS).register(entries -> {
            entries.addBefore(Items.COD, RNRItems.SQUID, RNRItems.COOKED_SQUID);
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS).register(entries -> {
            entries.addBefore(Items.GUSTER_POTTERY_SHERD, RNRItems.GILLS_POTTERY_SHERD);
        });
    }
}
