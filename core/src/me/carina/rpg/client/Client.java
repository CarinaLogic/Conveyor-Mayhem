package me.carina.rpg.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import me.carina.rpg.client.scenes.BattleScreen;
import me.carina.rpg.client.scenes.LoadingScreen;
import me.carina.rpg.common.AbstractGameInstance;

/**
 * Abstract implementation of a client.
 */
public abstract class Client extends AbstractGameInstance{
    Screen screen;
    public Client() {
        super("Client");
    }

    @Override
    public void create() {
        LoadingScreen loadingScreen = new LoadingScreen(this,Gdx.files.internal("core"));
        setScreen(loadingScreen);
    }

    @Override
    public void render() {
        if (screen != null) screen.render(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void resize(int width, int height) {
        if (screen != null) screen.resize(width,height);
    }

    @Override
    public void pause() {
        if (screen != null) screen.pause();
    }

    @Override
    public void resume() {
        if (screen != null) screen.resume();
    }

    @Override
    public void dispose() {
        if (screen != null) screen.dispose();
    }

    public void setScreen(Screen screen) {
        Screen oldScreen = this.screen;
        if (this.screen != null) this.screen.hide();
        this.screen = screen;
        if (oldScreen != null) {
            getLogger().debug("Current screen changed from "+oldScreen.getClass().getSimpleName()
                    +" to "+screen.getClass().getSimpleName());
        }
        else {
            getLogger().debug("Current screen changed to "+screen.getClass().getSimpleName());
        }
        if (this.screen != null) {
            this.screen.show();
            this.screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }
    }

    public Screen getScreen() {
        return screen;
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
