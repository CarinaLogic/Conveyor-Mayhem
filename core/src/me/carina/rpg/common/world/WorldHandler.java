package me.carina.rpg.common.world;

public abstract class WorldHandler implements PacketHandler{
    BaseWorld world;
    public WorldHandler(BaseWorld world){
        this.world = world;
    }
    @Override
    public abstract void send(Object object);

    @Override
    public abstract void recieve(Object object);

    public BaseWorld getWorld(){
        return world;
    }
}
