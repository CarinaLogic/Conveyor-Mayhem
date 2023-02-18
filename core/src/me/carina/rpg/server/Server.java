package me.carina.rpg.server;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import me.carina.rpg.common.file.AssetFilterProvider;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.world.ServerWorld;
import me.carina.rpg.common.world.AbstractGameInstance;
import me.carina.rpg.packets.C2SPacket;
import me.carina.rpg.packets.Packet;
import me.carina.rpg.server.tasks.AbstractTask;
import me.carina.rpg.server.tasks.LoadingTask;

public abstract class Server extends AbstractGameInstance {
    AbstractTask task;
    public Server() {
        super(new ServerWorld(),"Server");
    }

    @Override
    public void create() {
        task = new LoadingTask(this,Gdx.files.internal("rpg"));
    }

    @Override
    public void render() {
        if (task != null) task.run(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void resize(int width, int height) {
        //NOOP
    }

    @Override
    public void pause() {
        //NOOP
    }

    @Override
    public void resume() {
        //NOOP
    }

    @Override
    public void dispose() {
        //NOOP
    }

    @Override
    public boolean shouldLoad(FileHandle handle, Class<?> loadClass) {
        return !Texture.class.equals(loadClass);
    }

    @Override
    public boolean shouldPack() {
        return false;
    }

    public void setTask(AbstractTask task){
        this.task = task;
    }

    public AbstractTask getTask() {
        return task;
    }
}
