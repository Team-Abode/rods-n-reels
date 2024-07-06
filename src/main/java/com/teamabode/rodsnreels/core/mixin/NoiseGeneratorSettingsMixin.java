package com.teamabode.rodsnreels.core.mixin;

import com.teamabode.rodsnreels.RodsNReels;
import com.teamabode.rodsnreels.core.registry.RNRBiomes;
import net.minecraft.data.worldgen.SurfaceRuleData;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NoiseGeneratorSettings.class)
public class NoiseGeneratorSettingsMixin {
    private static final SurfaceRules.RuleSource GRAVEL = SurfaceRuleData.makeStateRule(Blocks.GRAVEL);
    private static final SurfaceRules.RuleSource TEST = SurfaceRuleData.makeStateRule(Blocks.REDSTONE_LAMP);

    @Inject(method = "surfaceRule", at = @At("RETURN"), cancellable = true)
    private void surfaceRule(CallbackInfoReturnable<SurfaceRules.RuleSource> cir)
    {
        SurfaceRules.RuleSource ruleSource = cir.getReturnValue();

        SurfaceRules.RuleSource oceanTrenchBiomeRule = SurfaceRules.ifTrue(SurfaceRules.isBiome(RNRBiomes.OCEAN_TRENCH), GRAVEL);
        SurfaceRules.RuleSource oceanTrenchFloorRule = SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, oceanTrenchBiomeRule);
        SurfaceRules.RuleSource oceanTrenchNoiseRule = SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SURFACE, -0.9, -0.1), oceanTrenchFloorRule);

        cir.setReturnValue(SurfaceRules.sequence(oceanTrenchNoiseRule, ruleSource));
    }
}
