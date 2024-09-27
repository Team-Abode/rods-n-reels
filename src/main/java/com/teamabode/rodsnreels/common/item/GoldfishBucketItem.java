package com.teamabode.rodsnreels.common.item;

import com.mojang.serialization.Codec;
import com.teamabode.rodsnreels.common.entity.goldfish.GoldfishEntity;
import com.teamabode.rodsnreels.common.entity.goldfish.brain.GoldfishUtils;
import com.teamabode.rodsnreels.common.entity.goldfish.variant.GoldfishBreed;
import com.teamabode.rodsnreels.common.entity.goldfish.variant.GoldfishColor;
import com.teamabode.rodsnreels.core.registry.RNREntityTypes;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.Bucketable;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.EntityBucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

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
        GoldfishEntity entity = RNREntityTypes.GOLDFISH.spawnFromItemStack(server, stack, null, pos, SpawnReason.BUCKET, true, false);

        if (entity instanceof Bucketable bucketable) {
            NbtComponent customData = stack.getOrDefault(DataComponentTypes.BUCKET_ENTITY_DATA, NbtComponent.DEFAULT);
            bucketable.copyDataFromNbt(customData.copyNbt());
            bucketable.setFromBucket(true);

            if (!GoldfishUtils.hasLikedPlayer(entity)) {
                GoldfishUtils.setLikedPlayer(entity, user.getUuid());
                server.sendEntityStatus(entity, (byte) 7);
            }
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        NbtComponent nbtComponent = stack.getOrDefault(DataComponentTypes.BUCKET_ENTITY_DATA, NbtComponent.DEFAULT);
        if (nbtComponent.isEmpty()) return;

        Optional<Integer> breed = nbtComponent.get(Codec.INT.fieldOf("breed")).result();
        Optional<Integer> color = nbtComponent.get(Codec.INT.fieldOf("color")).result();

        if (breed.isEmpty() || color.isEmpty()) return;

        Formatting[] formattings = {Formatting.ITALIC, Formatting.GRAY};
        String breedKey = "entity.rods_n_reels.goldfish.breed." + GoldfishBreed.byId(breed.get()).getName();
        String colorKey = "entity.rods_n_reels.goldfish.color." + GoldfishColor.byId(color.get()).getName();

        tooltip.add(Text.translatable(breedKey).formatted(formattings));
        tooltip.add(Text.translatable(colorKey).formatted(formattings));
    }
}
