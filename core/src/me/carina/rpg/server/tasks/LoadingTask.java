package me.carina.rpg.server.tasks;

import com.badlogic.gdx.files.FileHandle;
import me.carina.rpg.server.Server;

public class LoadingTask extends AbstractTask{
    FileHandle root;
    boolean queued = false;
    boolean loaded = false;
    public LoadingTask(Server server, FileHandle root) {
        super(server,false,true);
        this.root = root;
    }

    @Override
    public boolean run() {
        if (!queued) {
            server.getAssets().queue(root);
            queued = true;
            return false;
        }
        if (!loaded && server.getAssets().load()) {
            loaded = true;
            return false;
        }
        if (loaded){
            return true;
        }
        return false;
    }
}
