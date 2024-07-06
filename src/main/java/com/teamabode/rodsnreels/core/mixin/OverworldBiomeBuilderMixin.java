package com.teamabode.rodsnreels.core.mixin;

import com.mojang.datafixers.util.Pair;
import com.teamabode.rodsnreels.core.registry.RNRBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.OverworldBiomeBuilder;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(OverworldBiomeBuilder.class)
public abstract class OverworldBiomeBuilderMixin {
    @Shadow protected abstract void addSurfaceBiome(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter parameter, Climate.Parameter parameter2, Climate.Parameter parameter3, Climate.Parameter parameter4, Climate.Parameter parameter5, float f, ResourceKey<Biome> resourceKey);

    @Shadow @Final private Climate.Parameter FULL_RANGE;

    @Redirect(method = "addOffCoastBiomes", at = @At(value = "FIELD", target = "Lnet/minecraft/world/level/biome/OverworldBiomeBuilder;deepOceanContinentalness:Lnet/minecraft/world/level/biome/Climate$Parameter;", opcode = Opcodes.GETFIELD))
    public Climate.Parameter getDeepOceanContinentalness(OverworldBiomeBuilder instance) {
        return Climate.Parameter.span(-0.8f, -0.455f);
    }

    @Inject(method="addOffCoastBiomes", at = @At("TAIL"))
    private void addOffCoastBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, CallbackInfo ci) {
        this.addSurfaceBiome(consumer, this.FULL_RANGE, this.FULL_RANGE, RNRBiomes.OCEAN_TRENCH_CONTINENTALNESS, this.FULL_RANGE, this.FULL_RANGE, 0.0f, RNRBiomes.OCEAN_TRENCH);
    }
}
