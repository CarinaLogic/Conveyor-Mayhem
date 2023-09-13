package me.carina.conveyor.server;

public abstract class AbstractExternalServer extends Server {
    public abstract void open(int port);
    public abstract void close(String reason);
    public abstract boolean isValid();
    public abstract boolean isOpen();
}
