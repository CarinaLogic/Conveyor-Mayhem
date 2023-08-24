package me.carina.rpg.client.scenes;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import me.carina.rpg.client.actions.TimedRepeatingAction;

public abstract class GUIStage<T extends BaseScreen> extends GameStage<T> {
    Table table;
    public GUIStage(){
        super(new ExtendViewport(256,256));
    }

    @Override
    public void init() {
        table = new Table();
        addActor(table);
        table.setTouchable(Touchable.childrenOnly);
        table.setFillParent(true);
        init(table);
        setDebugAll(true);
    }

    public abstract void init(Table table);

    public <T extends Actor> Cell<T> add(T actor){
        return table.add(actor);
    }

}
