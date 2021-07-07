package me.andrew.silentbackground.gui;

import me.andrew.silentbackground.gui.widget.BackgroundSoundSliderWidget;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.TranslatableText;

public class BackgroundSoundOptionsScreen extends GameOptionsScreen {
    public BackgroundSoundOptionsScreen(Screen parent, GameOptions gameOptions) {
        super(parent, gameOptions, new TranslatableText("options.backgroundSounds.title"));
    }

    @Override
    protected void init() {
        int i = 0;
        this.addDrawableChild(new BackgroundSoundSliderWidget(this.client, this.width / 2 - 155 + i % 2 * 160, this.height / 6 - 12 + 24 * (i >> 1), SoundCategory.MASTER, 310));
        i += 2;

        for(SoundCategory soundCategory : SoundCategory.values()) {
            if (soundCategory != SoundCategory.MASTER) {
                this.addDrawableChild(new BackgroundSoundSliderWidget(this.client, this.width / 2 - 155 + i % 2 * 160, this.height / 6 - 12 + 24 * (i >> 1), soundCategory, 150));
                i++;
            }
        }

        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height / 6 + 168, 200, 20, ScreenTexts.DONE, (button) -> {
            this.client.openScreen(this.parent);
        }));
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 15, 16777215);
        super.render(matrices, mouseX, mouseY, delta);
    }
}
