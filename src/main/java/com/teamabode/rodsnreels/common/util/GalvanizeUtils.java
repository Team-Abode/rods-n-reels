package com.teamabode.rodsnreels.common.util;

import com.teamabode.rodsnreels.RodsNReels;
import com.teamabode.rodsnreels.core.registry.RNREnchantmentEffectComponentTypes;
import com.teamabode.rodsnreels.core.registry.RNRParticleTypes;
import net.minecraft.block.ShapeContext;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

import java.util.List;
import java.util.Optional;

public class GalvanizeUtils {
    public static final float GALVANIZE_RANGE = 8.0f;
    public static final float GALVANIZE_DAMAGE_DIVIDER = 2.0f;

    public static float getMaxTargets(ServerWorld world, ItemStack stack, Entity user) {
        ItemEnchantmentsComponent enchantmentsComponent = stack.getEnchantments();
        float count = 0.0f;

        for (var enchantmentEntry : enchantmentsComponent.getEnchantmentEntries()) {
            Enchantment enchantment = enchantmentEntry.getKey().value();
            int level = enchantmentEntry.getIntValue();
            var galvanizeEffects = enchantment.getEffect(RNREnchantmentEffectComponentTypes.TRIDENT_MAX_GALVANIZE_TARGETS);

            for (var effectEntry : galvanizeEffects) {
                var context = createContext(world, level, user, user.getPos());
                var effect = effectEntry.effect();

                if (!effectEntry.test(context)) continue;

                count = effect.apply(level, user.getRandom(), count);
            }
        }
        return count;
    }

    public static void applyDamageEffects(ServerWorld world, int count, float damage, TridentEntity trident, Entity attacked) {
        List<Entity> entities = world.getOtherEntities(
                attacked,
                trident.getBoundingBox().expand(GALVANIZE_RANGE),
                entity -> isAffected(trident, entity)
        );
        int remaining = count;
        Entity owner = trident.getOwner();

        for (Entity entity : entities) {
            if (remaining == 0) break;

            LivingEntity living = (LivingEntity) entity;
            living.damage(
                    world.getDamageSources().trident(trident, owner == null ? trident : owner),
                    damage / GALVANIZE_DAMAGE_DIVIDER
            );
            spawnParticles(world, trident.getPos(), living.getEyePos());
            remaining--;
        }
    }

    public static void spawnParticles(ServerWorld world, Vec3d origin, Vec3d targetPos) {
        Vec3d step = targetPos.subtract(origin).multiply(0.2d);
        Vec3d currentPos = origin;

        while (currentPos.distanceTo(targetPos) >= 0.15f) {
            RodsNReels.LOGGER.info("Distance: {}", currentPos.distanceTo(targetPos));
            currentPos = currentPos.add(step);
            world.spawnParticles(RNRParticleTypes.ZAP, currentPos.x, currentPos.y, currentPos.z, 1, 0.0d, 0.0d, 0.0d, 0);
        }
    }

    public static boolean isAffected(TridentEntity trident, Entity affected) {
        if (affected == trident.getOwner()) {
            return false;
        }
        if (EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR.and(EntityPredicates.VALID_LIVING_ENTITY).test(affected)) {
            return hasLineOfSight(trident.getWorld(), trident.getPos(), affected.getEyePos());
        }
        return false;
    }

    private static boolean hasLineOfSight(World world, Vec3d pos, Vec3d targetPos) {
        BlockHitResult blockHitResult = world.raycast(new RaycastContext(targetPos, pos, RaycastContext.ShapeType.VISUAL, RaycastContext.FluidHandling.NONE, ShapeContext.absent()));
        return blockHitResult.getBlockPos().equals(BlockPos.ofFloored(pos)) || blockHitResult.getType() == HitResult.Type.MISS;
    }

    public static LootContext createContext(ServerWorld world, int level, Entity entity, Vec3d pos) {
        LootContextParameterSet parameters = new LootContextParameterSet.Builder(world)
                .add(LootContextParameters.THIS_ENTITY, entity)
                .add(LootContextParameters.ENCHANTMENT_LEVEL, level)
                .add(LootContextParameters.ORIGIN, pos).build(LootContextTypes.ENCHANTED_ENTITY);
        return new LootContext.Builder(parameters).build(Optional.empty());
    }
}
