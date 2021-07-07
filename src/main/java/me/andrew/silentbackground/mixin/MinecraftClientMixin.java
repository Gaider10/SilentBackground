package me.andrew.silentbackground.mixin;

import me.andrew.silentbackground.SilentBackgroundMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.sound.SoundCategory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
    @Shadow @Final private SoundManager soundManager;

    @Shadow @Final public GameOptions options;

    @Inject(
            method = "onWindowFocusChanged",
            at = @At("RETURN")
    )
    public void onWindowFocusChanged(boolean focused, CallbackInfo ci) {
        if (this.soundManager == null) return;

        for(SoundCategory soundCategory : SoundCategory.values()) {
            this.soundManager.updateSoundVolume(soundCategory, SilentBackgroundMod.getModifiedVolume(soundCategory));
        }
    }
}
