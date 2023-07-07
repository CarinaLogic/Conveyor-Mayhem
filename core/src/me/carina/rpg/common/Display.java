package me.carina.rpg.common;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;

public interface Display {
    default void draw(Batch batch, float parentAlpha){
        
    }
    void addAction (Action action);
}
