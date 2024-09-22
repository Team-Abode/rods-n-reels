package com.teamabode.rodsnreels.datagen.server.tags;

import com.teamabode.rodsnreels.core.registry.RNREntityTypes;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.EntityTypeTags;

import java.util.concurrent.CompletableFuture;

public class RNREntityTypeTagProvider extends FabricTagProvider.EntityTypeTagProvider {

    public RNREntityTypeTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        this.aquatic();
        this.notScaryForPufferfish();
        this.canBreatheUnderWater();
    }

    private void aquatic() {
        this.getOrCreateTagBuilder(EntityTypeTags.AQUATIC)
                .setReplace(false)
                .add(RNREntityTypes.GOLDFISH);
    }

    private void notScaryForPufferfish() {
        this.getOrCreateTagBuilder(EntityTypeTags.NOT_SCARY_FOR_PUFFERFISH)
                .setReplace(false)
                .add(RNREntityTypes.GOLDFISH);
    }

    private void canBreatheUnderWater() {
        this.getOrCreateTagBuilder(EntityTypeTags.CAN_BREATHE_UNDER_WATER)
                .setReplace(false)
                .add(RNREntityTypes.GOLDFISH);
    }
}
