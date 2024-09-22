package com.teamabode.rodsnreels.core.registry;

import com.teamabode.rodsnreels.RodsNReels;
import com.teamabode.rodsnreels.common.item.GoldfishBucketItem;
import com.teamabode.rodsnreels.core.misc.RNRFoodComponents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Settings;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class RNRItems {
    public static final Item GILLS_POTTERY_SHERD = register("gills_pottery_sherd", new Settings());

    public static final Item SQUID = register("squid", new Settings().food(RNRFoodComponents.SQUID));
    public static final Item COOKED_SQUID = register("cooked_squid", new Settings().food(RNRFoodComponents.CALAMARI));

    public static final Item GOLDFISH = register("goldfish", new Settings().food(RNRFoodComponents.CALAMARI));
    public static final Item GOLDFISH_BUCKET = register("goldfish_bucket", new GoldfishBucketItem(new Settings()
            .maxCount(1)
            .component(DataComponentTypes.BUCKET_ENTITY_DATA, NbtComponent.DEFAULT)
    ));
    public static final Item GOLDFISH_SPAWN_EGG = register("goldfish_spawn_egg", new SpawnEggItem(RNREntityTypes.GOLDFISH, 14902052, 15284239, new Settings()));

    private static Item register(String name, Item item) {
        return Registry.register(Registries.ITEM, RodsNReels.id(name), item);
    }

    private static Item register(String name, Settings properties) {
        return register(name, new Item(properties));
    }

    public static void register() {
    }
}
