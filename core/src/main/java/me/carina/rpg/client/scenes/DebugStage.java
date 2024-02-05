package me.carina.rpg.client.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import me.carina.rpg.client.actions.TimedRepeatingAction;
import me.carina.rpg.client.ui.UILabel;

public class DebugStage extends GUIStage<BaseScreen>{
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
