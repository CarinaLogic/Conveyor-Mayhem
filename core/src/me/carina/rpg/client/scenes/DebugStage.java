package me.carina.rpg.client.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.NumberUtils;
import me.carina.rpg.Game;
import me.carina.rpg.client.actions.TimedRepeatingAction;
import me.carina.rpg.client.ui.UILabel;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.file.Path;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Formatter;

public class DebugStage extends GUIStage{
    UILabel label;
    @Override
    public void init(Table table) {
        label = new UILabel();
        label.addAction(new TimedRepeatingAction(1) {
            @SuppressWarnings("DefaultLocale")
            @Override
            public void act() {
                String s = "FPS: " + Gdx.graphics.getFramesPerSecond() + " " +
                        Gdx.graphics.getDeltaTime() / 1000 + "ms\n";
                label.setText(s);
            }
        });
        table.add(label).top().left();
        table.align(Align.topLeft);
    }
}
