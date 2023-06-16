package me.carina.rpg.client.scenes;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Action;
import me.carina.rpg.Game;
import me.carina.rpg.client.Client;

public class LoadingScreen extends BaseScreen{
    LoadingStage stage;
    FileHandle root;
    boolean queued = false;
    boolean loaded = false;

    public LoadingScreen(FileHandle root) {
        super();
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
                    Game.getInstance().getAssets().queue(root);
                    queued = true;
                    return false;
                }
                if (!loaded && Game.getInstance().getAssets().load()) {
                    loaded = true;
                    return false;
                }
                if (loaded){
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public boolean canChangeScreen() {
        return loaded;
    }
}
