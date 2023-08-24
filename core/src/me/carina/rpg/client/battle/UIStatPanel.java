package me.carina.rpg.client.battle;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import me.carina.rpg.Game;
import me.carina.rpg.client.misc.UIColor;
import me.carina.rpg.client.ui.UILabel;
import me.carina.rpg.client.ui.UIProgressBar;
import me.carina.rpg.client.ui.UITableView;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.unit.Unit;
import me.carina.rpg.common.unit.stat.HPMaxStat;
import me.carina.rpg.common.unit.stat.HPStat;
import me.carina.rpg.common.unit.stat.SPMaxStat;
import me.carina.rpg.common.unit.stat.SPStat;

public class UIStatPanel extends UITableView implements Display<Unit> {
    Unit unit;
    UILabel nameLabel;
    UIHpBar hpBar;
    UISpBar spBar;
    public UIStatPanel(Unit unit){
        this.unit = unit;
        nameLabel = new UILabel();
        nameLabel.addAction(Actions.forever(Actions.run(() -> nameLabel.text(
                unit.getName()
        ))));
        add(nameLabel).left().colspan(2);
        row();
        add(new UILabel().text("HP")).padRight(4);
        hpBar = new UIHpBar();
        hpBar.addAction(Actions.forever(Actions.run(() -> hpBar.value(
                unit.getStats().getStat(HPStat.class).get() * 100 /
                        unit.getStats().getStat(HPMaxStat.class).get()))));
        add(hpBar).expandX().fillX();
        row();
        add(new UILabel().text("SP")).padRight(4);
        spBar = new UISpBar();
        spBar.addAction(Actions.forever(Actions.run(() -> spBar.value(
                unit.getStats().getStat(SPStat.class).get() * 100 /
                        unit.getStats().getStat(SPMaxStat.class).get()))));
        add(spBar).expandX().fillX();
    }

    @Override
    public Unit getFeature() {
        return unit;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Game.getInstance().getContext().add(getFeature());
        super.draw(batch, parentAlpha);
    }

    public static class UIHpBar extends UIProgressBar{
        public UIHpBar(){
            super();
            color(UIColor.hp);
        }
    }

    @Override
    public float getPrefWidth() {
        return 72;
    }

    @Override
    public float getPrefHeight() {
        return 48;
    }

    public static class UISpBar extends UIProgressBar{
        public UISpBar(){
            super();
            color(UIColor.sp);
        }
    }
}
