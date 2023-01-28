package me.carina.rpg.packets;

import com.badlogic.gdx.Gdx;
import me.carina.rpg.client.Client;

public class S2CMessagePacket extends S2CPacket{
    public S2CMessagePacket(){}
    String message;
    public S2CMessagePacket(String message){
        this.message = message;
    }
    @Override
    public void onRecieve(Client client) {
        Gdx.app.log("Client",message);
    }
}
