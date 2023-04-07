package me.carina.rpg;

import me.carina.rpg.server.Connection;
import org.java_websocket.WebSocket;

public class CommonExternalConnection extends Connection {
    WebSocket webSocket;
    public CommonExternalConnection(WebSocket webSocket){
        this.webSocket = webSocket;
    }
}
