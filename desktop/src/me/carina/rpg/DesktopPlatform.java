package me.carina.rpg;

import me.carina.rpg.server.AbstractExternalServer;

public class DesktopPlatform implements Platform{
    @Override
    public boolean canHostServer() {
        return true;
    }

    @Override
    public AbstractExternalServer newServer() {
        return new ExternalServer();
    }
}
