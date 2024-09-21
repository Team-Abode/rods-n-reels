package com.teamabode.rodsnreels.core.registry;

import com.teamabode.rodsnreels.RodsNReels;
import com.teamabode.rodsnreels.common.item.GoldfishBucketItem;
import com.teamabode.rodsnreels.core.misc.RNRFoodProperties;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.component.CustomData;

public class RNRItems {
    public static final Item GILLS_POTTERY_SHERD = register("gills_pottery_sherd", new Properties());

    public static final Item SQUID = register("squid", new Properties().food(RNRFoodProperties.SQUID));
    public static final Item COOKED_SQUID = register("cooked_squid", new Properties().food(RNRFoodProperties.CALAMARI));

    public static final Item GOLDFISH = register("goldfish", new Properties().food(RNRFoodProperties.CALAMARI));
    public static final Item GOLDFISH_BUCKET = register("goldfish_bucket", new GoldfishBucketItem(
            new Properties().component(DataComponents.BUCKET_ENTITY_DATA, CustomData.EMPTY)
    ));
    public static final Item GOLDFISH_SPAWN_EGG = register("goldfish_spawn_egg", new SpawnEggItem(RNREntityTypes.GOLDFISH, 14902052, 15284239, new Properties()));

    private static Item register(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, RodsNReels.id(name), item);
    }

    private static Item register(String name, Properties properties) {
        return register(name, new Item(properties));
    }

    public static void register() {
    }
}
