package me.carina.rpg.client.misc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

public class Movable extends DragListener {

    public Movable(){
        this.setTapSquareSize(0.3f);
    }
    @Override
    public void dragStart(InputEvent event, float x, float y, int pointer) {
    }

    @Override
    public void drag(InputEvent event, float x, float y, int pointer) {
        event.getListenerActor().setPosition(event.getStageX()-getDragStartX(),event.getStageY()-getDragStartY());
    }

    @Override
    public void dragStop(InputEvent event, float x, float y, int pointer) {
    }
}
