package com.teamabode.rodsnreels.common.item;

import com.teamabode.rodsnreels.common.entity.goldfish.Goldfish;
import com.teamabode.rodsnreels.core.registry.RNREntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.Nullable;

public class GoldfishBucketItem extends MobBucketItem {
    public GoldfishBucketItem(Properties properties) {
        super(RNREntityTypes.GOLDFISH, Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, properties);
    }

    @Override
    public void checkExtraContent(@Nullable Player user, Level level, ItemStack stack, BlockPos pos) {
        if (level instanceof ServerLevel) {
            this.spawn((ServerLevel) level, user, stack, pos);
            level.gameEvent(user, GameEvent.ENTITY_PLACE, pos);
        }
    }

    private void spawn(ServerLevel server, Player user, ItemStack stack, BlockPos pos) {
        Goldfish entity = RNREntityTypes.GOLDFISH.spawn(server, stack, null, pos, MobSpawnType.BUCKET, true, false);

        if (entity instanceof Bucketable bucketable) {
            CustomData customData = stack.getOrDefault(DataComponents.BUCKET_ENTITY_DATA, CustomData.EMPTY);
            bucketable.loadFromBucketTag(customData.copyTag());
            bucketable.setFromBucket(true);

            if (entity.getOwnerUUID() == null) {
                entity.setOwnerUUID(user.getUUID());
                server.broadcastEntityEvent(entity, (byte) 7);
            }
        }
    }
}
