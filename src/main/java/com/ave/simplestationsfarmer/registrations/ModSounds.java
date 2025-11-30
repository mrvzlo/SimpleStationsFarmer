package com.ave.simplestationsfarmer.registrations;

import java.util.function.Supplier;

import com.ave.simplestationsfarmer.SimpleStationsFarmer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSounds {
        public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister
                        .create(ForgeRegistries.SOUND_EVENTS, SimpleStationsFarmer.MODID);

        public static final Supplier<SoundEvent> WORK_SOUND = SOUND_EVENTS.register(
                        "work_sound",
                        () -> SoundEvent
                                        .createVariableRangeEvent(ResourceLocation
                                                        .fromNamespaceAndPath(SimpleStationsFarmer.MODID,
                                                                        "work_sound")));

}
