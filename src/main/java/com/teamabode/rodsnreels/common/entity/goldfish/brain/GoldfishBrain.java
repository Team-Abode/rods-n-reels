package com.teamabode.rodsnreels.common.entity.goldfish.brain;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.teamabode.rodsnreels.common.entity.goldfish.GoldfishEntity;
import net.minecraft.entity.ai.brain.*;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.ai.brain.task.*;

import java.util.List;
import java.util.Set;

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
                Pair.of(2, WalkTowardsLookTargetTask.create(GoldfishUtils::getLookTarget, GoldfishUtils::canFollowLikedPlayer, 4, 12, 2.5f))
        ));
    }

    public static void updateActivities(GoldfishEntity goldfish) {
        goldfish.getBrain().resetPossibleActivities(List.of(
                Activity.IDLE
        ));
    }
}
