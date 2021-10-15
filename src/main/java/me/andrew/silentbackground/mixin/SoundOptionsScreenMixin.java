package me.andrew.silentbackground.mixin;

import me.andrew.silentbackground.accessor.ScreenAccessor;
import me.andrew.silentbackground.gui.BackgroundSoundOptionsScreen;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.screen.option.SoundOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

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
        List<Drawable> drawables = ((ScreenAccessor) this).silentbackground$getDrawables();
        int i = 11;
        int x;
        int y;
        nextPos: while(true) {
            x = this.width / 2 - 155 + i % 2 * 160;
            y = this.height / 6 - 12 + 24 * (i >> 1);

            for(Drawable drawable : drawables) {
                if(drawable instanceof ClickableWidget clickableWidget) {
                    if(clickableWidget.x == x && clickableWidget.y == y) {
                        i++;
                        continue nextPos;
                    }
                }
            }

            break;
        }

        if(i != 11) {
            int otherY = y + 24;
            for(Drawable drawable : drawables) {
                if(drawable instanceof ClickableWidget clickableWidget) {
                    if(clickableWidget.y > this.height / 6 - 12 + 24 * (11 >> 1)) {
                        clickableWidget.y = otherY;
                        otherY += 24;
                    }
                }
            }
        }

        this.addDrawableChild(new ButtonWidget(x, y, 150, 20, new TranslatableText("options.backgroundSounds"), (button) -> {
            this.client.openScreen(new BackgroundSoundOptionsScreen(this, this.gameOptions));
        }));
    }
}
