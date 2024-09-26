package com.teamabode.rodsnreels.core.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.teamabode.rodsnreels.common.util.GalvanizeUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TridentEntity.class)
public class TridentEntityMixin {

    @Inject(
            method = "onEntityHit",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/enchantment/EnchantmentHelper;onTargetDamaged(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/Entity;Lnet/minecraft/entity/damage/DamageSource;Lnet/minecraft/item/ItemStack;)V"
            )
    )
    private void rodsnreels$onEntityHit(EntityHitResult result, CallbackInfo ci, @Local float damage) {
        TridentEntity $this = TridentEntity.class.cast(this);
        float enchantedCount = GalvanizeUtils.getMaxTargets((ServerWorld) $this.getWorld(), $this.getWeaponStack(), $this.getOwner());

        if (enchantedCount >= 0.0f) {
            GalvanizeUtils.applyDamageEffects((ServerWorld) $this.getWorld(), MathHelper.floor(enchantedCount), damage, $this, result.getEntity());
        }
    }
}
