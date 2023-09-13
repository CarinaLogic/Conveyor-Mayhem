package me.carina.conveyor;

import me.carina.conveyor.server.AbstractExternalServer;

public class AndroidPlatform implements Platform{
    @Override
    public boolean canHostServer() {
        return true;
    }

    @Override
    public AbstractExternalServer newServer() {
        return new ExternalServer();
    }
}
