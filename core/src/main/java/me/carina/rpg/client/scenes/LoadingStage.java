package me.carina.rpg.client.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class LoadingStage extends GameStage<LoadingScreen>{
    Table table;
    Image image;

    public LoadingStage() {
        super(new ExtendViewport(10,10));
    }

    @Override
    public void init() {
        table = new Table();
        addActor(table);
        image = new Image(new Texture(Gdx.files.internal("core/ui/loading.png")));
        table.add(image).size(3,2).center();
        table.setFillParent(true);
    }
}
