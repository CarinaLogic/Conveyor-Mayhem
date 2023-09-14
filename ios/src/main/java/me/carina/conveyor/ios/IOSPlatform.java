package me.carina.conveyor.ios;

import me.carina.conveyor.ExternalServer;
import me.carina.conveyor.Platform;
import me.carina.conveyor.server.AbstractExternalServer;

public class IOSPlatform implements Platform {
    @Override
    public boolean canHostServer() {
        return true;
    }

    @Override
    public AbstractExternalServer newServer() {
        return new ExternalServer();
    }
}
