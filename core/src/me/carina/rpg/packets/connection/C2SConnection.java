package me.carina.rpg.packets.connection;

import me.carina.rpg.client.Client;
import me.carina.rpg.common.AbstractGameInstance;
import me.carina.rpg.server.Server;

public abstract class C2SConnection implements Connection{
    Client client;
    public C2SConnection(Client client){
        this.client = client;
    }

    public Client getClient() {
        return client;
    }
}
