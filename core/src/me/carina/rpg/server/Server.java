package me.carina.rpg.server;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import me.carina.rpg.common.AbstractGameInstance;
import me.carina.rpg.packets.S2CTargetPacket;
import me.carina.rpg.server.tasks.AbstractTask;
import me.carina.rpg.server.tasks.LoadingTask;

public abstract class Server extends AbstractGameInstance {
    Array<AbstractTask> tasks = new Array<>();
    public Server() {
        super("Server");
    }

    @Override
    public void create() {
        addTask(new LoadingTask(this,Gdx.files.internal("rpg")));
    }

    @Override
    public void render() {
        for (AbstractTask task : tasks) {
            task.tick(Gdx.graphics.getDeltaTime());
        }
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
    public void addTask(AbstractTask task){
        tasks.add(task);
    }
    public void removeTask(AbstractTask task){
        tasks.removeValue(task,true);
    }

    @Override
    public boolean shouldLoad(FileHandle handle, Class<?> loadClass) {
        return !Texture.class.equals(loadClass);
    }

    @Override
    public boolean shouldPack() {
        return false;
    }

    @Override
    public void send(Object object) {
        if (object instanceof S2CTargetPacket) {
            S2CTargetPacket targetPacket = (S2CTargetPacket) object;
            for (Connection target : targetPacket.getTargets()) {
                send(object,target);
            }
        }
        else {
            sendAll(object);
        }
    }
    public abstract Array<? extends Connection> getClients();
    public abstract void send(Object object, Connection connection);
    public abstract void sendAll(Object object);

    public abstract void open(int port);
    public abstract void close(String reason);
    public abstract boolean isOpen();

}
