package me.carina.rpg.client.ui;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import me.carina.rpg.Game;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.file.Path;

public class UIVerticalListView extends UITableView{
    ScrollPane pane;
    Table innerTable;
    public UIVerticalListView(){
        pane = new ScrollPane(null);
        super.add(pane);
        innerTable = new Table();
        pane.setActor(innerTable);
    }

    public <T extends Actor> Cell<T> add(T actor) {
        Cell<T> cell = innerTable.add(actor);
        cell.expandX().fillX();
        innerTable.row();
        return cell;
    }
}
