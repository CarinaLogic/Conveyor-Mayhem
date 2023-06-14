package me.carina.rpg.common;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Action;

public interface Display {
    void draw (Batch batch, float parentAlpha);
    void addAction (Action action);
}
