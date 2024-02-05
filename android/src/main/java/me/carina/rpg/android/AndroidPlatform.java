package me.carina.rpg.android;

import me.carina.rpg.ExternalServer;
import me.carina.rpg.Platform;
import me.carina.rpg.server.AbstractExternalServer;

public class AndroidPlatform implements Platform {
    @Override
    public boolean canHostServer() {
        return true;
    }

    @Override
    public AbstractExternalServer newServer() {
        return new ExternalServer();
    }
}
