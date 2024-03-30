package me.carina.rpg.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Queue;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import me.carina.rpg.client.scenes.BaseScreen;
import me.carina.rpg.client.scenes.LoadingScreen;
import me.carina.rpg.common.AbstractGameInstance;
import me.carina.rpg.common.Displays;

/**
 * Abstract implementation of a client.
 */
public abstract class Client extends AbstractGameInstance{
    Queue<BaseScreen> screenQueue = new Queue<>();
    Queue<BaseScreen> pendingScreenQueue = new Queue<>();

    Displays displays = new Displays();
    public Client() {
        super("Client");
    }
    @Override
    public void create() {
        LoadingScreen loadingScreen = new LoadingScreen(Gdx.files.internal("core"));
        queueScreen(loadingScreen);
    }

    @Override
    public void tick() {
        if (!pendingScreenQueue.isEmpty() && (screenQueue.isEmpty() || screenQueue.last().canChangeScreen())){
            if (!screenQueue.isEmpty()) screenQueue.last().hide();
            pendingScreenQueue.first().show();
            screenQueue.addLast(pendingScreenQueue.first());
            pendingScreenQueue.removeFirst();
        }
        if (!screenQueue.isEmpty()){
            screenQueue.last().render(Gdx.graphics.getDeltaTime());
        }
        displays.tick();
    }

    @Override
    public void resize(int width, int height) {
        screenQueue.forEach(s -> s.resize(width, height));
    }

    @Override
    public void pause() {
        screenQueue.forEach(BaseScreen::pause);
    }

    @Override
    public void resume() {
        screenQueue.forEach(BaseScreen::resume);
    }

    @Override
    public void dispose() {
        screenQueue.forEach(BaseScreen::dispose);
    }

    public void queueScreen(BaseScreen screen) {
        pendingScreenQueue.addLast(screen);
    }

    public BaseScreen getScreen() {
        return screenQueue.last();
    }

    public <T extends BaseScreen> T getScreen(Class<T> type){
        BaseScreen screen = getScreen();
        if (ClassReflection.isInstance(type,screen)){
            //noinspection unchecked
            return (T) screen;
        }
        return null;
    }

    @Override
    public boolean isClient() {
        return true;
    }

    public Displays getDisplays() {
        return displays;
    }

    @Override
    public boolean shouldPack() {
        return true;
    }

    @Override
    public boolean shouldLoad(FileHandle handle, Class<?> loadClass) {
        return true;
    }

}
