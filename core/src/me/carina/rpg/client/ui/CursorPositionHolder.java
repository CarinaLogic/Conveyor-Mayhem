package me.carina.rpg.client.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;

public interface CursorPositionHolder {
    boolean isActive();
    Actor getSelected();
    void setSelected(Actor actor);
}
