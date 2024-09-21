package com.teamabode.rodsnreels.core.mixin;

import com.teamabode.rodsnreels.core.registry.RNRBiomes;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.gen.noise.NoiseParametersKeys;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChunkGeneratorSettings.class)
public class ChunkGeneratorSettingsMixins {
    @Unique
    private static final MaterialRules.MaterialRule GRAVEL = MaterialRules.block(Blocks.GRAVEL.getDefaultState());

    @Inject(method = "surfaceRule", at = @At("RETURN"), cancellable = true)
    private void surfaceRule(CallbackInfoReturnable<MaterialRules.MaterialRule> cir)
    {
        MaterialRules.MaterialRule ruleSource = cir.getReturnValue();

        MaterialRules.MaterialRule oceanTrenchBiomeRule = MaterialRules.condition(MaterialRules.biome(RNRBiomes.OCEAN_TRENCH), GRAVEL);
        MaterialRules.MaterialRule oceanTrenchFloorRule = MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, oceanTrenchBiomeRule);
        MaterialRules.MaterialRule oceanTrenchNoiseRule = MaterialRules.condition(MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE, -0.9, -0.1), oceanTrenchFloorRule);

        cir.setReturnValue(MaterialRules.sequence(oceanTrenchNoiseRule, ruleSource));
    }
}
