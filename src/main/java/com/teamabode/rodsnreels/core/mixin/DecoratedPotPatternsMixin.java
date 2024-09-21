package com.teamabode.rodsnreels.core.mixin;

import com.teamabode.rodsnreels.core.registry.RNRDecoratedPotPatterns;
import com.teamabode.rodsnreels.core.registry.RNRItems;
import net.minecraft.block.DecoratedPotPattern;
import net.minecraft.block.DecoratedPotPatterns;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DecoratedPotPatterns.class)
public class DecoratedPotPatternsMixin {

    @Inject(method = "fromSherd", at = @At("HEAD"), cancellable = true)
    private static void getPatternFromItem(Item item, CallbackInfoReturnable<RegistryKey<DecoratedPotPattern>> cir) {
        if (item == RNRItems.GILLS_POTTERY_SHERD) {
            cir.setReturnValue(RNRDecoratedPotPatterns.GILLS);
        }
    }
}
