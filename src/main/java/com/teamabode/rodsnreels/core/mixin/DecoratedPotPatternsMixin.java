package com.teamabode.rodsnreels.core.mixin;

import com.teamabode.rodsnreels.core.registry.RNRDecoratedPotPatterns;
import com.teamabode.rodsnreels.core.registry.RNRItems;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.DecoratedPotPattern;
import net.minecraft.world.level.block.entity.DecoratedPotPatterns;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DecoratedPotPatterns.class)
public class DecoratedPotPatternsMixin {

    @Inject(method = "getPatternFromItem", at = @At("HEAD"), cancellable = true)
    private static void getPatternFromItem(Item item, CallbackInfoReturnable<ResourceKey<DecoratedPotPattern>> cir) {
        if (item == RNRItems.GILLS_POTTERY_SHERD) {
            cir.setReturnValue(RNRDecoratedPotPatterns.GILLS_POTTERY_PATTERN);
        }
    }

    @Inject(method = "bootstrap", at = @At("TAIL"))
    private static void arts_and_crafts_bootstrap(Registry<DecoratedPotPattern> registry, CallbackInfoReturnable<String> cir) {
        //NORMAL
        RNRDecoratedPotPatterns.register(registry, RNRDecoratedPotPatterns.GILLS_POTTERY_PATTERN, "gills_pottery_pattern");
    }
}
