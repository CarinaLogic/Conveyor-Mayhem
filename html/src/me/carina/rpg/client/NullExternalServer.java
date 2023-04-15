package me.carina.rpg.client;

import com.badlogic.gdx.utils.Array;
import me.carina.rpg.server.AbstractExternalServer;

public class NullExternalServer extends AbstractExternalServer {
    @Override
    public void open(int port) {

    }

    @Override
    public void close(String reason) {

    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public boolean isOpen() {
        return false;
    }

}
