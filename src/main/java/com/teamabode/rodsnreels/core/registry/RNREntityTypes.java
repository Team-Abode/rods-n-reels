package com.teamabode.rodsnreels.core.registry;

import com.teamabode.rodsnreels.RodsNReels;
import com.teamabode.rodsnreels.common.entity.goldfish.GoldfishEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class RNREntityTypes {

    public static final EntityType<GoldfishEntity> GOLDFISH = register(
            "goldfish",
            EntityType.Builder.create(GoldfishEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.5f, 0.3f)
                    .eyeHeight(0.195f)
                    .maxTrackingRange(4)
    );

    public static void register() {
        FabricDefaultAttributeRegistry.register(GOLDFISH, GoldfishEntity.createGoldfishAttributes());
    }

    private static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> builder) {
        return Registry.register(Registries.ENTITY_TYPE, RodsNReels.id(name), builder.build(name));
    }
}
