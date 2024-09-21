package com.teamabode.rodsnreels.common.entity.goldfish.goals;

import com.teamabode.rodsnreels.RodsNReels;
import com.teamabode.rodsnreels.common.entity.goldfish.Goldfish;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class SwimTowardsOwnerGoal extends Goal {
    private final Goldfish goldfish;
    @Nullable
    private LivingEntity owner;
    private final PathNavigation navigation;
    private int timeToRecalcPath;
    private final float distance;

    public SwimTowardsOwnerGoal(Goldfish goldfish, float distance) {
        this.goldfish = goldfish;
        this.navigation = goldfish.getNavigation();
        this.distance = distance;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    public boolean canUse() {
        if (!(goldfish.getOwner() instanceof Player player)) {
            return false;
        }
        if (this.goldfish.unableToFollowOwner()) {
            return false;
        }
        if (this.goldfish.distanceToSqr(player) < (double) (this.distance * this.distance)) {
            return false;
        }
        if (!player.isInWater()) {
            return false;
        }
        else {
            this.owner = player;
            return true;
        }
    }

    public boolean canContinueToUse() {
        if (this.navigation.isDone()) {
            return false;
        }
        if (this.goldfish.unableToFollowOwner()) {
            return false;
        }
        return true;
    }

    public void start() {
        this.timeToRecalcPath = 0;
        RodsNReels.LOGGER.info("Following Owner :D");
    }

    public void stop() {
        this.owner = null;
        this.navigation.stop();
    }

    public void tick() {
        this.goldfish.getLookControl().setLookAt(this.owner, 10.0F, (float) this.goldfish.getMaxHeadXRot());

        if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = this.adjustedTickDelay(10);
            this.navigation.moveTo(this.owner, 2.0f);
        }
    }
}
