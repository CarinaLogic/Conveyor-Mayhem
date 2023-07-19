package me.carina.rpg.server;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import me.carina.rpg.common.AbstractGameInstance;
import me.carina.rpg.common.battle.BattleMap;
import me.carina.rpg.common.util.Array;
import me.carina.rpg.packets.C2SPacket;
import me.carina.rpg.packets.connection.Connection;
import me.carina.rpg.server.tasks.AbstractTask;
import me.carina.rpg.server.tasks.BattleMapUpdateTask;
import me.carina.rpg.server.tasks.ClientConnectTask;
import me.carina.rpg.server.tasks.LoadingTask;

public abstract class Server extends AbstractGameInstance {
    Array<AbstractTask> tasks = new Array<>();
    public Server() {
        super("Server");
    }

    @Override
    public void create() {
        addTask(new LoadingTask(Gdx.files.internal("core")));
        addTask(new BattleMapUpdateTask(new BattleMap()));
    }

    @SuppressWarnings("unchecked")
    public <T extends AbstractTask> T getTask (Class<T> cls){
        return (T)tasks.firstMatch(t -> ClassReflection.isInstance(cls,t));
    }

    @Override
    public void tick() {
        boolean p = false;
        for (AbstractTask task : tasks) {
            if (task.isPrioritized()){
                task.tick(Gdx.graphics.getDeltaTime());
                p = true;
            }
        }
        if (!p) {
            for (AbstractTask task : tasks) {
                task.tick(Gdx.graphics.getDeltaTime());
            }
        }
    }

    @Override
    public boolean isClient() {
        return false;
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
    public void addConnection(Connection connection) {
        super.addConnection(connection);
        addTask(new ClientConnectTask(connection));
    }

    @Override
    public boolean shouldLoad(FileHandle handle, Class<?> loadClass) {
        return !Texture.class.equals(loadClass);
    }

    @Override
    public boolean shouldPack() {
        return false;
    }

    public abstract void open(int port);
    public abstract void close(String reason);
    public abstract boolean isOpen();

}
