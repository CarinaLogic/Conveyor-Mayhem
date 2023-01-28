package me.carina.rpg.server;

import com.badlogic.gdx.Gdx;
import me.carina.rpg.packets.C2SPacket;
import me.carina.rpg.packets.S2CPacket;

public abstract class Server {
    public abstract void send(Object object);
    public void recieve(Object object){
        if (object instanceof C2SPacket) ((C2SPacket)object).onRecieve(this);
        else {
            Gdx.app.error("Server","Received packet ("+object.toString()+") is not a member of C2SPacket, ignoring");
        }
    }
    public void tick(){

    }
}
