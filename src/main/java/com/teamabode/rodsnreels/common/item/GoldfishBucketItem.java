package com.teamabode.rodsnreels.common.item;

import com.teamabode.rodsnreels.common.entity.goldfish.Goldfish;
import com.teamabode.rodsnreels.core.registry.RNREntityTypes;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.Bucketable;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.EntityBucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class GoldfishBucketItem extends EntityBucketItem {
    public GoldfishBucketItem(net.minecraft.item.Item.Settings properties) {
        super(RNREntityTypes.GOLDFISH, Fluids.WATER, SoundEvents.ITEM_BUCKET_EMPTY_FISH, properties);
    }

    @Override
    public void onEmptied(@Nullable PlayerEntity user, World level, ItemStack stack, BlockPos pos) {
        if (level instanceof ServerWorld) {
            this.spawn((ServerWorld) level, user, stack, pos);
            level.emitGameEvent(user, GameEvent.ENTITY_PLACE, pos);
        }
    }

    private void spawn(ServerWorld server, PlayerEntity user, ItemStack stack, BlockPos pos) {
        Goldfish entity = RNREntityTypes.GOLDFISH.spawnFromItemStack(server, stack, null, pos, SpawnReason.BUCKET, true, false);

        if (entity instanceof Bucketable bucketable) {
            NbtComponent customData = stack.getOrDefault(DataComponentTypes.BUCKET_ENTITY_DATA, NbtComponent.DEFAULT);
            bucketable.copyDataFromNbt(customData.copyNbt());
            bucketable.setFromBucket(true);

            server.sendEntityStatus(entity, (byte) 7);
        }
    }
}
