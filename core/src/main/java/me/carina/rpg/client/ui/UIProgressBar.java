package me.carina.rpg.client.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import me.carina.rpg.Game;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.file.Path;

import java.util.function.IntSupplier;
import java.util.function.Supplier;

public class UIProgressBar extends ProgressBar {
    //TODO fix progressbar texture not in atlas
    IntSupplier maxSupplier = ()->100;
    IntSupplier valueSupplier = ()->0;
    Supplier<Color> colorSupplier = ()->Color.WHITE;
    public UIProgressBar() {
        super(0,100,1,false,getPrefStyle());
    }
    public UIProgressBar supplyMax(IntSupplier supplier){
        maxSupplier = supplier;
        return this;
    }
    public UIProgressBar supplyValue(IntSupplier supplier){
        valueSupplier = supplier;
        return this;
    }
    public UIProgressBar supplyColor(Supplier<Color> supplier){
        colorSupplier = supplier;
        return this;
    }

    @Override
    public void act(float delta) {
        setRange(0,maxSupplier.getAsInt());
        setValue(valueSupplier.getAsInt());
        setColor(colorSupplier.get());
        super.act(delta);
    }

    @Override
    public float getPrefWidth() {
        if (isVertical()) {
            Drawable knob = getStyle().knob, bg = getBackgroundDrawable();
            return Math.max(knob == null ? 0 : knob.getMinWidth(), bg == null ? 0 : bg.getMinWidth());
        } else
            return 32;
    }
    @Override
    public float getPrefHeight () {
        if (isVertical())
            return 32;
        else {
            Drawable knob = getStyle().knob, bg = getBackgroundDrawable();
            return Math.max(knob == null ? 0 : knob.getMinHeight(), bg == null ? 0 : bg.getMinHeight());
        }
    }

    public static ProgressBarStyle getPrefStyle(){
        ProgressBarStyle style = new ProgressBarStyle();
        style.background = Game.getClient().getAssets().get(new Path("core", AssetGroup.ui, "progressbar"), Drawable.class);
        style.knobBefore = Game.getClient().getAssets().get(new Path("core", AssetGroup.ui, "progressknobbefore"), Drawable.class);
        return style;
    }
}
