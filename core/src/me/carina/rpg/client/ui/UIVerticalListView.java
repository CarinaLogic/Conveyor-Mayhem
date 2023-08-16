package me.carina.rpg.client.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;

public class UIVerticalListView extends UITableView{
    public UIVerticalListView(){

    }

    @Override
    public <T extends Actor> Cell<T> add(T actor) {
        Cell<T> cell = super.add(actor);
        cell.expandX().fillX();
        row();
        return cell;
    }
}
