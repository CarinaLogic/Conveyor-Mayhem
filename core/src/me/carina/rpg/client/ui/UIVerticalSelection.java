package me.carina.rpg.client.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class UIVerticalSelection extends UIVerticalListView implements CursorPositionHolder{
    ButtonGroup<Button> group;
    int position;
    boolean active;
    public UIVerticalSelection(){
        super();
        group = new ButtonGroup<>();
        setMaxSelectable(1);
    }
    @Override
    public <T extends Actor> Cell<T> add(T actor) {
        if (actor instanceof Button) {
            Button button = (Button) actor;
            group.add(button);
        }
        return super.add(actor);
    }
    public void setMaxSelectable(int count){
        group.setMaxCheckCount(count);
    }
    public void setMinSelectable(int count){
        group.setMinCheckCount(count);
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public Actor getSelected() {
        return group.getButtons().get(position);
    }

    @Override
    public void setSelected(Actor actor) {
        if (actor instanceof Button) {
            Button button = (Button) actor;
            int i = group.getButtons().indexOf(button,true);
            if (i != 1) position = i;
        }
    }
}
