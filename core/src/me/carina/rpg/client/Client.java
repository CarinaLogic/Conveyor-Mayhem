package me.carina.rpg.client;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import me.carina.rpg.packets.C2SPacket;
import me.carina.rpg.packets.S2CPacket;

/**
 * Abstract implementation of a client.
 */
public abstract class Client extends Game {
    @Override
    public void create() {

    }
    public abstract void send(Object object);
    public void recieve(Object object){
        if (object instanceof S2CPacket) ((S2CPacket)object).onRecieve(this);
        else {
            Gdx.app.error("Client","Received packet ("+object.toString()+") is not a member of C2SPacket, ignoring");
        }
    }
}
