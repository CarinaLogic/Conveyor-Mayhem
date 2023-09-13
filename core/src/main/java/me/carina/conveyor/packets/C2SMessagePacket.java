package me.carina.conveyor.packets;

import com.badlogic.gdx.Gdx;
import me.carina.conveyor.packets.connection.S2CConnection;
import me.carina.conveyor.server.Server;

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
