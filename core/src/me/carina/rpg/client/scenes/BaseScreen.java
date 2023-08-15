package me.carina.rpg.client.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.Queue;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import me.carina.rpg.Game;
import me.carina.rpg.client.Client;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public abstract class BaseScreen implements Screen {
    InputMultiplexer multiplexer = new InputMultiplexer();
    Queue<BiConsumer<BaseScreen,Client>> delayedInitFunctions = new Queue<>();
    CanvasStage canvasStage;

    public BaseScreen(){
        Gdx.input.setInputProcessor(multiplexer);
        canvasStage = new CanvasStage();
        addStage(canvasStage);
    }

    public abstract void init();
    @Override
    public void show(){
        init();
        delayedInitFunctions.forEach(func -> func.accept(this, (Client) Game.getInstance()));
        delayedInitFunctions.clear();
        if (Game.getInstance().getLogger().getLevel() == Logger.DEBUG && !(this instanceof LoadingScreen)){
            addStage(new DebugStage());
        }
    }

    public void queueInit(BiConsumer<BaseScreen,Client> func){
        delayedInitFunctions.addLast(func);
    }


    public void addStage(GameStage stage){
        stage.init();
        multiplexer.addProcessor(stage);
    }

    public CanvasStage getCanvas() {
        return canvasStage;
    }

    @Override
    public void render(float delta) {
        for (InputProcessor processor : multiplexer.getProcessors()) {
            if (processor instanceof Stage){
                Stage stage = (Stage) processor;
                stage.act();
            }
        }
        ScreenUtils.clear(Color.BLACK);
        for (InputProcessor processor : multiplexer.getProcessors()) {
            if (processor instanceof Stage) {
                Stage stage = (Stage) processor;
                stage.getViewport().apply();
                stage.draw();
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        for (InputProcessor processor : multiplexer.getProcessors()) {
            if (processor instanceof Stage){
                Stage stage = (Stage) processor;
                stage.getViewport().update(width,height,true);
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

    public abstract boolean canChangeScreen();
}
