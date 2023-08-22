package me.carina.rpg.client.battle;

import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.RemoveListenerAction;
import me.carina.rpg.client.misc.UIColor;
import me.carina.rpg.client.ui.UILabel;
import me.carina.rpg.client.ui.UIProgressBar;
import me.carina.rpg.client.ui.UITableView;

public class UIStatsPanel extends UITableView {
    //TODO register actual value later
    public UIStatsPanel(){
        add(new UILabel().text("Chara Name")).left().colspan(2);
        row();
        add(new UILabel().text("HP")).padRight(4);
        add(new UIHpBar().value(75)).expandX().fillX();
        row();
        add(new UILabel().text("SP")).padRight(4);
        add(new UISpBar().value(45)).expandX().fillX();
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
