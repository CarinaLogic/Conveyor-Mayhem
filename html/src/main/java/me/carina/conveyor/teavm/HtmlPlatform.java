package me.carina.conveyor.teavm;

import me.carina.conveyor.Platform;
import me.carina.conveyor.server.AbstractExternalServer;

public class HtmlPlatform implements Platform {
    @Override
    public boolean canHostServer() {
        return false;
    }

    @Override
    public AbstractExternalServer newServer() {
        return null;
    }
}
