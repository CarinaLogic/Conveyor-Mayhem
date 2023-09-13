package me.carina.conveyor.packets;

import com.badlogic.gdx.Gdx;
import me.carina.conveyor.client.Client;
import me.carina.conveyor.packets.connection.C2SConnection;

public class S2CMessagePacket extends S2CPacket{
    public S2CMessagePacket(){}
    String message;
    public S2CMessagePacket(String message){
        this.message = message;
    }
    @Override
    public void onRecieve(Client client, C2SConnection connection) {
        Gdx.app.log("Client",message);
    }
}
