package com.teamabode.rodsnreels.core.registry;

import com.teamabode.rodsnreels.RodsNReels;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;

public class RNRSoundEvents {
    public static final SoundEvent ENTITY_GOLDFISH_FLOP = register("entity.goldfish.flop");
    public static final SoundEvent ENTITY_GOLDFISH_HURT = register("entity.goldfish.hurt");
    public static final SoundEvent ENTITY_GOLDFISH_DEATH = register("entity.goldfish.death");

    public static void register() {

    }

    private static SoundEvent register(String name) {
        return Registry.register(Registries.SOUND_EVENT, RodsNReels.id(name), SoundEvent.of(RodsNReels.id(name)));
    }
}
