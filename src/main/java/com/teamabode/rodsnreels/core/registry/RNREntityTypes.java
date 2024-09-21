package com.teamabode.rodsnreels.core.registry;

import com.teamabode.rodsnreels.RodsNReels;
import com.teamabode.rodsnreels.common.entity.goldfish.Goldfish;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class RNREntityTypes {

    public static final EntityType<Goldfish> GOLDFISH = register(
            "goldfish",
            EntityType.Builder.create(Goldfish::new, SpawnGroup.CREATURE)
                    .dimensions(0.5f, 0.3f)
                    .eyeHeight(0.195f)
                    .maxTrackingRange(4)
    );

    public static void register() {
        FabricDefaultAttributeRegistry.register(GOLDFISH, FishEntity.createFishAttributes());
    }

    private static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> builder) {
        return Registry.register(Registries.ENTITY_TYPE, RodsNReels.id(name), builder.build(name));
    }
}
