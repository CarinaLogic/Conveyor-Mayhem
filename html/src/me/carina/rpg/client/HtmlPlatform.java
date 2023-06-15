package me.carina.rpg.client;

import me.carina.rpg.Platform;
import me.carina.rpg.server.AbstractExternalServer;

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
