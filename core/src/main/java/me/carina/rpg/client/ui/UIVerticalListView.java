package me.carina.rpg.client.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.SnapshotArray;
import me.carina.rpg.common.ArrayDisplayHandler;
import me.carina.rpg.common.Feature;
import me.carina.rpg.common.util.Array;

import java.util.function.Supplier;

public class UIVerticalListView extends UITableView implements CursorPositionHolder{
    int position;
    Supplier<Color> colorSupplier = ()->Color.WHITE;
    ScrollPane pane;
    InnerTable innerTable;
    public UIVerticalListView(){
        pane = new ScrollPane(null);
        super.add(pane);
        innerTable = new InnerTable(this);
        pane.setActor(innerTable);
    }

    @Override
    public void act(float delta) {
        setColor(colorSupplier.get());

        super.act(delta);
    }

    public <T extends Actor> Cell<T> add(T actor) {
        Cell<T> cell = innerTable.add(actor);
        cell.expandX().fillX();
        innerTable.row();
        return cell;
    }

    @Override
    public SnapshotArray<Actor> getChildren() {
        return innerTable.getChildren();
    }

    @Override
    public Actor getSelected() {
        return innerTable.getSelected();
    }

    @Override
    public void setSelected(Actor actor) {
        innerTable.setSelected(actor);
    }

    private static class InnerTable extends Table implements CursorPositionHolder{
        UIVerticalListView parentList;
        private InnerTable(UIVerticalListView listView){
            this.parentList = listView;
        }
        @Override
        public Actor getSelected() {
            return getChildren().get(parentList.position);
        }

        @Override
        public void setSelected(Actor actor) {
            int i = getChildren().indexOf(actor,true);
            if (i != 1) parentList.position = i;
        }
    }
}
