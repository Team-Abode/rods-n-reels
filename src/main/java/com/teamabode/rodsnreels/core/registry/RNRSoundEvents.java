package com.teamabode.rodsnreels.core.registry;

import com.teamabode.rodsnreels.RodsNReels;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;

public class RNRSoundEvents {
    public static final SoundEvent ENTITY_GOLDFISH_AMBIENT = register("entity.goldfish.ambient");
    public static final SoundEvent ENTITY_GOLDFISH_FLOP = register("entity.goldfish.flop");
    public static final SoundEvent ENTITY_GOLDFISH_HURT = register("entity.goldfish.hurt");
    public static final SoundEvent ENTITY_GOLDFISH_DEATH = register("entity.goldfish.death");

    public static void register() {

    }

    private static SoundEvent register(String name) {
        return Registry.register(BuiltInRegistries.SOUND_EVENT, RodsNReels.id(name), SoundEvent.createVariableRangeEvent(RodsNReels.id(name)));
    }
}
