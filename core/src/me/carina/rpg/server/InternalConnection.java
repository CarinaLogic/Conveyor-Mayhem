package me.carina.rpg.server;

import me.carina.rpg.client.Client;
import me.carina.rpg.client.InternalClient;

public class InternalConnection extends Connection{
    InternalClient client;
    public InternalConnection(InternalClient client){
        this.client = client;
    }
}
