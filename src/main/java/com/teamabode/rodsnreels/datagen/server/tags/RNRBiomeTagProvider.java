package com.teamabode.rodsnreels.datagen.server.tags;

import com.teamabode.rodsnreels.core.registry.RNRBiomes;
import com.teamabode.rodsnreels.core.tag.RNRBiomeTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

import java.util.concurrent.CompletableFuture;

public class RNRBiomeTagProvider extends FabricTagProvider<Biome> {

    public RNRBiomeTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.BIOME, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup lookup) {
        this.getOrCreateTagBuilder(RNRBiomeTags.HAS_RIVER_ARCHAEOLOGY)
                .forceAddTag(BiomeTags.IS_RIVER);

        this.getOrCreateTagBuilder(RNRBiomeTags.COPPER_DIVING_BELL_HAS_STRUCTURE)
                .add(RNRBiomes.OCEAN_TRENCH)
                .forceAddTag(BiomeTags.IS_DEEP_OCEAN);

        this.getOrCreateTagBuilder(RNRBiomeTags.TRENCH_RUINS_HAS_STRUCTURE)
                .add(RNRBiomes.OCEAN_TRENCH);

        this.getOrCreateTagBuilder(RNRBiomeTags.SWEET_BERRIES_HAS_FISHING_JUNK)
                .forceAddTag(BiomeTags.IS_TAIGA);

        this.getOrCreateTagBuilder(RNRBiomeTags.MANGROVE_PROPAGULE_HAS_FISHING_JUNK)
                .add(BiomeKeys.MANGROVE_SWAMP);
    }
}
