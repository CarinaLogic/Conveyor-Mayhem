package me.carina.rpg.client.scenes;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class GUIStage extends Stage {
    Table table;
    public GUIStage(){
        super(new ExtendViewport(16,16));
        table = new Table();
        addActor(table);
        table.setFillParent(true);
    }
    public <T extends Actor> Cell<T> add(T actor){
        return table.add(actor);
    }

}
