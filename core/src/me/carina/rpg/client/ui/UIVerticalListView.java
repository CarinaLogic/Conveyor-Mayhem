package me.carina.rpg.client.ui;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.SnapshotArray;
import me.carina.rpg.Game;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.file.Path;

public class UIVerticalListView extends UITableView implements CursorPositionHolder{
    int position;
    ScrollPane pane;
    InnerTable innerTable;
    public UIVerticalListView(){
        pane = new ScrollPane(null);
        super.add(pane);
        innerTable = new InnerTable(this);
        pane.setActor(innerTable);
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
