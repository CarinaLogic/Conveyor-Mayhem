package me.carina.rpg.client.battle;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.RemoveListenerAction;
import me.carina.rpg.Game;
import me.carina.rpg.client.misc.UIColor;
import me.carina.rpg.client.ui.UILabel;
import me.carina.rpg.client.ui.UIProgressBar;
import me.carina.rpg.client.ui.UITableView;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.unit.Unit;

public class UIStatsPanel extends UITableView implements Display<Unit> {
    Unit unit;
    UIHpBar hpBar;
    UISpBar spBar;
    public UIStatsPanel(Unit unit){
        this.unit = unit;
        add(new UILabel().text("Chara Name")).left().colspan(2);
        row();
        add(new UILabel().text("HP")).padRight(4);
        hpBar = new UIHpBar();
        add(hpBar).expandX().fillX();
        row();
        add(new UILabel().text("SP")).padRight(4);
        spBar = new UISpBar();
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
