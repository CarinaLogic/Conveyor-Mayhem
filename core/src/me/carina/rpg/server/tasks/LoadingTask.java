package me.carina.rpg.server.tasks;

import com.badlogic.gdx.files.FileHandle;
import me.carina.rpg.server.Server;

public class LoadingTask extends AbstractTask{
    FileHandle root;
    boolean queued = false;
    boolean loaded = false;
    AbstractTask nextTask = null;
    public LoadingTask(Server server, FileHandle root) {
        super(server);
        this.root = root;
    }

    @Override
    public void run(float delta) {
        if (!queued) {
            server.getAssets().queue(root);
            queued = true;
            return;
        }
        if (!loaded && server.getAssets().load()) {
            loaded = true;
            return;
        }
        if (loaded && nextTask != null){
            server.setTask(nextTask);
        }
    }
}
