package me.carina.rpg.client.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class UIVerticalSelection extends UIVerticalListView implements CursorPositionHolder, CursorHandler{
    ButtonGroup<Button> group;
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
    public boolean goUp() {
        position--;
        if (position < 0) position = group.getButtons().size;
        return true;
    }

    @Override
    public boolean goDown() {
        position++;
        if (position >= group.getButtons().size) position = 0;
        return true;
    }

    @Override
    public boolean enter() {
        return CursorHandler.super.enter();
    }
}
