package com.teamabode.rodsnreels.common.entity.goldfish;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.*;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.ai.brain.task.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class GoldfishBrain {
    public static final List<MemoryModuleType<?>> MEMORY_MODULES = List.of(
            MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE,
            MemoryModuleType.HURT_BY,
            MemoryModuleType.IS_PANICKING,
            MemoryModuleType.LIKED_PLAYER,
            MemoryModuleType.LOOK_TARGET,
            MemoryModuleType.PATH,
            MemoryModuleType.VISIBLE_MOBS,
            MemoryModuleType.WALK_TARGET
    );
    public static final List<SensorType<? extends Sensor<? super GoldfishEntity>>> SENSORS = List.of(
            SensorType.HURT_BY
    );

    public static Brain.Profile<GoldfishEntity> createProfile() {
        return Brain.createProfile(MEMORY_MODULES, SENSORS);
    }

    public static Brain<GoldfishEntity> create(Brain<GoldfishEntity> brain) {
        addCoreActivities(brain);
        addIdleActivities(brain);

        brain.setCoreActivities(Set.of(Activity.CORE));
        brain.setDefaultActivity(Activity.IDLE);
        return brain;
    }

    public static void addCoreActivities(Brain<GoldfishEntity> brain) {
        brain.setTaskList(Activity.CORE, 0, ImmutableList.of(
                new FleeTask<>(2.0f),
                new LookAroundTask(45, 90),
                new MoveToTargetTask()
        ));
    }

    public static void addIdleActivities(Brain<GoldfishEntity> brain) {
        brain.setTaskList(Activity.IDLE, ImmutableList.of(
                Pair.of(1, StrollTask.createDynamicRadius(1.0f)),
                Pair.of(2, WalkTowardsLookTargetTask.create(GoldfishBrain::getLikedPlayer, GoldfishBrain::canFollowLikedPlayer, 4, 12, 2.5f))
        ));
    }

    public static Optional<LookTarget> getLikedPlayer(LivingEntity goldfish) {
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

    public static boolean canFollowLikedPlayer(LivingEntity goldfish) {
        return !goldfish.hasVehicle();
    }

    public static void setLikedPlayer(GoldfishEntity goldfish, PlayerEntity player) {
        goldfish.getBrain().remember(MemoryModuleType.LIKED_PLAYER, player.getUuid());
    }

    public static void updateActivities(GoldfishEntity goldfish) {
        goldfish.getBrain().resetPossibleActivities(List.of(
                Activity.IDLE
        ));
    }
}
