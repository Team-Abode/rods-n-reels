package com.teamabode.rodsnreels.core.registry;

import com.teamabode.rodsnreels.RodsNReels;
import com.teamabode.rodsnreels.core.misc.RNRFoodProperties;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;

public class RNRItems {

    public static final Item GILLS_POTTERY_SHERD = register("gills_pottery_sherd", new Item(new Item.Properties()));

    public static final Item RAW_SQUID = register("raw_squid", new Item(new Item.Properties().food(RNRFoodProperties.RAW_SQUID)));
    public static final Item CALAMARI = register("calamari", new Item(new Item.Properties().food(RNRFoodProperties.CALAMARI)));

    private static <T extends Item> T register(String name, T item) {
        return Registry.register(BuiltInRegistries.ITEM, RodsNReels.id(name), item);
    }

    public static void register() {
    }
}
