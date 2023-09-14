package me.carina.conveyor.lwjgl3;

import me.carina.conveyor.ExternalServer;
import me.carina.conveyor.Platform;
import me.carina.conveyor.server.AbstractExternalServer;

public class DesktopPlatform implements Platform {
    @Override
    public boolean canHostServer() {
        return true;
    }

    @Override
    public AbstractExternalServer newServer() {
        return new ExternalServer();
    }
}
