package me.carina.conveyor;

import me.carina.conveyor.server.AbstractExternalServer;

public interface Platform {
    boolean canHostServer();
    AbstractExternalServer newServer();
}
