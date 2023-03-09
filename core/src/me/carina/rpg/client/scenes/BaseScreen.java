package me.carina.rpg.client.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import me.carina.rpg.client.Client;

public abstract class BaseScreen implements Screen {
    InputMultiplexer multiplexer = new InputMultiplexer();
    Client game;

    public BaseScreen(Client game){
        this.game = game;
        Gdx.input.setInputProcessor(multiplexer);
    }

    public abstract void show();


    public Stage addStage(Stage stage){
        multiplexer.addProcessor(stage);
        return stage;
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        for (InputProcessor processor : multiplexer.getProcessors()) {
            if (processor instanceof Stage stage) stage.act();
        }
        for (InputProcessor processor : multiplexer.getProcessors()) {
            if (processor instanceof Stage stage) stage.draw();
        }
    }

    @Override
    public void resize(int width, int height) {
        for (InputProcessor processor : multiplexer.getProcessors()) {
            if (processor instanceof Stage stage){
                stage.getViewport().update(width,height);
                stage.getCamera().update();
            }
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
