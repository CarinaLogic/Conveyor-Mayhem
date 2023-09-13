package me.carina.conveyor.client.scenes;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

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
    }

    public abstract void init(Table table);
}
