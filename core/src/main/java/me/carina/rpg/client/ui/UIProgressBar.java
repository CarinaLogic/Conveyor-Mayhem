package me.carina.rpg.client.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import me.carina.rpg.Game;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.file.Path;

public class UIProgressBar extends ProgressBar {
    public UIProgressBar() {
        super(0,100,0.1f,false,getPrefStyle());
    }
    public UIProgressBar color(Color color){
        setColor(color);
        return this;
    }
    public UIProgressBar value(float percent){
        setValue(percent);
        return this;
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
