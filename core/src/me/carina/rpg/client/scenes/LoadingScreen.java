package me.carina.rpg.client.scenes;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Action;
import me.carina.rpg.client.Client;

public class LoadingScreen extends BaseScreen{

    LoadingStage stage;
    FileHandle root;
    Screen nextScreen = null;
    boolean queued = false;
    boolean loaded = false;

    public LoadingScreen(Client game, FileHandle root) {
        super(game);
        this.root = root;
    }

    @Override
    public void show() {
        stage = new LoadingStage();
        addStage(stage);
        stage.table.addAction(new Action() {
            @Override
            public boolean act(float delta) {
                if (!queued) {
                    game.getAssets().queue(root);
                    queued = true;
                    return false;
                }
                if (!loaded && game.getAssets().load()) {
                    loaded = true;
                    return false;
                }
                if (loaded && nextScreen != null){
                    game.setScreen(nextScreen);
                    return true;
                }
                return false;
            }
        });
    }

    public void setNextScreen(Screen nextScreen) {
        this.nextScreen = nextScreen;
    }
}
