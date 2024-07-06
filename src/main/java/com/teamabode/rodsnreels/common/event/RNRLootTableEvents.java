package com.teamabode.rodsnreels.common.event;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;

public class RNRLootTableEvents {

    public static void modifyVanillaLootTables() {
        LootTableEvents.MODIFY.register((id, builder, source) -> {
            if (BuiltInLootTables.FISHING_JUNK.equals(id)) {
                /*
                HolderLookup.RegistryLookup<Biome> registryLookup = this.registries.lookupOrThrow(Registries.BIOME);
                builder.modifyPools(itemEntry -> {
                    itemEntry.with((LootItem.lootTableItem(Items.LILY_PAD).setWeight(25)
                            .when(LocationCheck.checkLocation(net.minecraft.advancements.critereon.LocationPredicate.Builder.location()
                                    .setBiomes(HolderSet.direct(new Holder[]{registryLookup.getOrThrow(Biomes.JUNGLE), registryLookup.getOrThrow(Biomes.SPARSE_JUNGLE), registryLookup.getOrThrow(Biomes.BAMBOO_JUNGLE)}))))).build());
                });

                 */
            }
        });
    }
}
