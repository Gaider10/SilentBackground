package me.andrew.silentbackground.mixin;

import me.andrew.silentbackground.gui.BackgroundSoundOptionsScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.screen.option.SoundOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SoundOptionsScreen.class)
public abstract class SoundOptionsScreenMixin extends GameOptionsScreen {
    public SoundOptionsScreenMixin(Screen parent, GameOptions gameOptions, Text title) {
        super(parent, gameOptions, title);
    }

    @Inject(
            method = "init",
            at = @At("RETURN")
    )
    public void onInit(CallbackInfo ci) {
        this.addButton(new ButtonWidget(this.width / 2 - 155 + 11 % 2 * 160, this.height / 6 - 12 + 24 * (11 >> 1), 150, 20, new TranslatableText("options.backgroundSounds"), (button) -> {
            this.client.openScreen(new BackgroundSoundOptionsScreen(this, this.gameOptions));
        }));
    }
}
