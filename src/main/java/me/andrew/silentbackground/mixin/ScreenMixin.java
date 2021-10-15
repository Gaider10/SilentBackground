package me.andrew.silentbackground.mixin;

import me.andrew.silentbackground.accessor.ScreenAccessor;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(Screen.class)
public abstract class ScreenMixin implements ScreenAccessor {
    @Shadow @Final private List<Drawable> drawables;

    @Override
    public List<Drawable> silentbackground$getDrawables() {
        return this.drawables;
    }
}
