package me.carina.rpg.server.tasks;

import com.badlogic.gdx.files.FileHandle;
import me.carina.rpg.Game;

public class LoadingTask extends AbstractTask{
    FileHandle root;
    boolean queued = false;
    boolean loaded = false;
    public LoadingTask(FileHandle root) {
        prioritize();
        this.root = root;
        timed();
    }

    @Override
    public boolean run() {
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
}
