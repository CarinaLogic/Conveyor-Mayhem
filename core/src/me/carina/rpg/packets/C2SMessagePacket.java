package me.carina.rpg.packets;

import com.badlogic.gdx.Gdx;
import me.carina.rpg.packets.connection.C2SConnection;
import me.carina.rpg.packets.connection.S2CConnection;
import me.carina.rpg.server.Server;

public class C2SMessagePacket extends C2SPacket{
    String message;
    public C2SMessagePacket(){}
    public C2SMessagePacket(String message){
        this.message = message;
    }
    @Override
    public void onRecieve(Server server, S2CConnection connection) {
        Gdx.app.log("Server",message);
    }
}
