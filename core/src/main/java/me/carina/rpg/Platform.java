package me.carina.rpg;

import me.carina.rpg.server.AbstractExternalServer;

public interface Platform {
    boolean canHostServer();
    AbstractExternalServer newServer();
}
