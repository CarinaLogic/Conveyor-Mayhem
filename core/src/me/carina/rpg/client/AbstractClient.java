package me.carina.rpg.client;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

/**
 * Abstract implementation of a client.
 */
public abstract class AbstractClient extends Game {
    @Override
    public void create() {

    }
    public abstract void send(Object object);
    public void recieve(Object object){
        Gdx.app.debug("Client", "Received packet " + object.toString() + " of type " + object.getClass().getSimpleName());
    }
}
