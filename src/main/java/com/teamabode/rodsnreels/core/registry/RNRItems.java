package com.teamabode.rodsnreels.core.registry;

import com.teamabode.rodsnreels.RodsNReels;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;

public class RNRItems {

    public static final Item GILLS_POTTERY_SHERD = register("gills_pottery_sherd", new Item(new Item.Properties()));

    private static <T extends Item> T register(String name, T item) {
        return Registry.register(BuiltInRegistries.ITEM, RodsNReels.id(name), item);
    }

    public static void register() {
    }
}
