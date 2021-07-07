package me.andrew.silentbackground.gui.widget;

import me.andrew.silentbackground.SilentBackgroundMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.widget.OptionSliderWidget;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class BackgroundSoundSliderWidget extends OptionSliderWidget {
    private final SoundCategory category;

    public BackgroundSoundSliderWidget(MinecraftClient client, int x, int y, SoundCategory category, int width) {
        super(client.options, x, y, width, 20, SilentBackgroundMod.backgroundVolumeLevels.get(category));
        this.category = category;
        this.updateMessage();
    }

    protected void updateMessage() {
        Text text = (float)this.value == 0.0f ? ScreenTexts.OFF : new LiteralText((int)(this.value * 100.0D) + "%");
        this.setMessage((new TranslatableText("soundCategory." + this.category.getName())).append(": ").append(text));
    }

    protected void applyValue() {
        SilentBackgroundMod.setBackgroundVolume(this.category, (float) this.value);
    }
}
