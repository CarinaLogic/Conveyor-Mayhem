package me.carina.rpg.server;

public abstract class AbstractExternalServer extends AbstractServer{
    public abstract void open(int port);
    public abstract void close(String reason);
    public abstract boolean isValid();
}
