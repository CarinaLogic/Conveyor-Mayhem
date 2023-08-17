package me.carina.rpg.client.ui;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import me.carina.rpg.Game;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.file.Path;

public class UIVerticalListView extends ScrollPane{
    Table innerTable;
    public UIVerticalListView(){
        super(null,getPaneStyle());
        innerTable = new Table();
        setActor(innerTable);
        innerTable.pad(6);
    }

    public <T extends Actor> Cell<T> add(T actor) {
        Cell<T> cell = innerTable.add(actor);
        cell.expandX().fillX();
        innerTable.row();
        return cell;
    }

    public static ScrollPane.ScrollPaneStyle getPaneStyle(){
        ScrollPane.ScrollPaneStyle style = new ScrollPane.ScrollPaneStyle();
        style.background =Game.getClient().getAssets().get(new Path("core", AssetGroup.ui, "frame"), NinePatchDrawable.class);
         return style;
    }
}
