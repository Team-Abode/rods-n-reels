package com.teamabode.rodsnreels.common.entity.goldfish;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.EntityLookTarget;
import net.minecraft.entity.ai.brain.LookTarget;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

import java.util.Optional;
import java.util.UUID;

public class GoldfishUtils {

    public static Optional<UUID> getLikedPlayer(GoldfishEntity goldfish) {
        return goldfish.getBrain().getOptionalRegisteredMemory(MemoryModuleType.LIKED_PLAYER);
    }

    public static void setLikedPlayer(GoldfishEntity goldfish, UUID id) {
        goldfish.getBrain().remember(MemoryModuleType.LIKED_PLAYER, id);
    }

    public static boolean hasLikedPlayer(GoldfishEntity goldfish) {
        return getLikedPlayer(goldfish).isPresent();
    }

    public static boolean canFollowLikedPlayer(LivingEntity goldfish) {
        return !goldfish.hasVehicle();
    }

    public static Optional<LookTarget> getLookTarget(LivingEntity goldfish) {
        Brain<?> brain = goldfish.getBrain();
        World world = goldfish.getWorld();
        Optional<UUID> likedPlayer = brain.getOptionalRegisteredMemory(MemoryModuleType.LIKED_PLAYER);
        if (likedPlayer.isEmpty()) return Optional.empty();

        PlayerEntity player = world.getPlayerByUuid(likedPlayer.get());
        if (player != null && player.isInsideWaterOrBubbleColumn()) {
            return Optional.of(new EntityLookTarget(player, true));
        }
        return Optional.empty();
    }
}
