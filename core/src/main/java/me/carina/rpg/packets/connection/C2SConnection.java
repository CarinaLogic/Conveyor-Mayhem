package me.carina.rpg.packets.connection;

import me.carina.rpg.client.Client;

public abstract class C2SConnection extends Connection{
    Client client;
    public C2SConnection(Client client){
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

}
