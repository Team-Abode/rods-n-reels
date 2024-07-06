package com.teamabode.rodsnreels.datagen.server;

import com.teamabode.rodsnreels.core.registry.RNRBiomes;
import com.teamabode.rodsnreels.core.registry.tag.RNRBiomeTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.world.level.biome.Biomes;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class RNRBiomeTagsProvider extends BiomeTagsProvider {
    public RNRBiomeTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(packOutput, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider arg) {
        appendHasSuspiciousBlock();
    }

    private void appendHasSuspiciousBlock() {
        this.tag(RNRBiomeTags.HAS_SUSPICIOUS_BLOCK)
                .add(Biomes.RIVER)
                .add(RNRBiomes.OCEAN_TRENCH);
    }

}
