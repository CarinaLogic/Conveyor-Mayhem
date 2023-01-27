package me.carina.rpg.server;

import com.badlogic.gdx.Gdx;

public abstract class Server {
    public abstract void send(Object object);
    public void recieve(Object object){
        Gdx.app.debug("Server", "Received packet " + object.toString() + " of type " + object.getClass().getSimpleName());
    }
    public void tick(){

    }
}
