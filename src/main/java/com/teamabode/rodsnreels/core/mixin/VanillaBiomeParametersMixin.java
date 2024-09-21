package com.teamabode.rodsnreels.core.mixin;

import com.mojang.datafixers.util.Pair;
import com.teamabode.rodsnreels.core.registry.RNRBiomes;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.minecraft.world.biome.source.util.VanillaBiomeParameters;

@Mixin(VanillaBiomeParameters.class)
public abstract class VanillaBiomeParametersMixin {
    @Shadow protected abstract void writeBiomeParameters(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> parameters, MultiNoiseUtil.ParameterRange temperature, MultiNoiseUtil.ParameterRange humidity, MultiNoiseUtil.ParameterRange continentalness, MultiNoiseUtil.ParameterRange erosion, MultiNoiseUtil.ParameterRange weirdness, float offset, RegistryKey<Biome> biome);
    @Shadow @Final private MultiNoiseUtil.ParameterRange defaultParameter;

    @Unique
    private static final MultiNoiseUtil.ParameterRange OCEAN_TRENCH_CONTINENTALNESS = MultiNoiseUtil.ParameterRange.of(-0.90f, -0.70f);

    @Redirect(
            method = "writeOceanBiomes",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/world/biome/source/util/VanillaBiomeParameters;deepOceanContinentalness:Lnet/minecraft/world/biome/source/util/MultiNoiseUtil$ParameterRange;",
                    opcode = Opcodes.GETFIELD
            )
    )
    public MultiNoiseUtil.ParameterRange rodsnreels$getDeepOceanContinentalness(VanillaBiomeParameters instance) {
        return MultiNoiseUtil.ParameterRange.of(-0.70f, -0.455f);
    }

    @Inject(
            method = "writeOceanBiomes",
            at = @At("TAIL")
    )
    private void rodsnreels$writeOceanBiomes(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> consumer, CallbackInfo ci) {
        if (System.getProperty("fabric-api.datagen") != null) return;

        this.writeBiomeParameters(consumer, this.defaultParameter, this.defaultParameter, OCEAN_TRENCH_CONTINENTALNESS, this.defaultParameter, this.defaultParameter, 0.0f, RNRBiomes.OCEAN_TRENCH);
    }
}
