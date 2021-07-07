package me.andrew.silentbackground.mixin;

import me.andrew.silentbackground.SilentBackgroundMod;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.sound.SoundSystem;
import net.minecraft.sound.SoundCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SoundSystem.class)
public abstract class SoundSystemMixin {
    @Redirect(
            method = "getSoundVolume",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/option/GameOptions;getSoundVolume(Lnet/minecraft/sound/SoundCategory;)F"
            )
    )
    public float redirectGetSoundVolume1(GameOptions options, SoundCategory soundCategory) {
        return SilentBackgroundMod.getModifiedVolume(soundCategory);
    }

    @Redirect(
            method = "start",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/option/GameOptions;getSoundVolume(Lnet/minecraft/sound/SoundCategory;)F"
            )
    )
    public float redirectGetSoundVolume2(GameOptions options, SoundCategory soundCategory) {
        return SilentBackgroundMod.getModifiedVolume(soundCategory);
    }

    @Redirect(
            method = "tick()V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/option/GameOptions;getSoundVolume(Lnet/minecraft/sound/SoundCategory;)F"
            )
    )
    public float redirectGetSoundVolume3(GameOptions options, SoundCategory soundCategory) {
        return SilentBackgroundMod.getModifiedVolume(soundCategory);
    }
}
