package com.teamabode.rodsnreels.core.registry;

import com.teamabode.rodsnreels.RodsNReels;
import com.teamabode.rodsnreels.common.entity.goldfish.Goldfish;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.animal.AbstractFish;

public class RNREntityTypes {

    public static final EntityType<Goldfish> GOLDFISH = register(
            "goldfish",
            EntityType.Builder.of(Goldfish::new, MobCategory.CREATURE)
                    .sized(0.5f, 0.3f)
                    .eyeHeight(0.195f)
                    .clientTrackingRange(4)
    );

    public static void register() {
        FabricDefaultAttributeRegistry.register(GOLDFISH, AbstractFish.createAttributes());
    }

    private static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> builder) {
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, RodsNReels.id(name), builder.build(name));
    }
}
