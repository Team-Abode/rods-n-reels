package com.teamabode.rodsnreels.datagen.server;

import com.teamabode.rodsnreels.core.registry.RNRBiomes;
import com.teamabode.rodsnreels.core.registry.tag.RNRBiomeTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.world.level.biome.Biomes;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class RNRBiomeTagsProvider extends FabricTagProvider {
    public RNRBiomeTagsProvider(FabricDataOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(packOutput, Registries.BIOME, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider arg) {
        appendHasSuspiciousBlock();

        this.getOrCreateTagBuilder(RNRBiomeTags.HAS_COPPER_DIVING_BELL)
                .addOptional(RNRBiomes.OCEAN_TRENCH);

        this.getOrCreateTagBuilder(RNRBiomeTags.HAS_OCEAN_CITY_RUINS)
                .addOptional(RNRBiomes.OCEAN_TRENCH);
    }

    private void appendHasSuspiciousBlock() {
        this.getOrCreateTagBuilder(RNRBiomeTags.HAS_SUSPICIOUS_BLOCK)
                .add(Biomes.RIVER)
                .addOptional(RNRBiomes.OCEAN_TRENCH);
    }

}
