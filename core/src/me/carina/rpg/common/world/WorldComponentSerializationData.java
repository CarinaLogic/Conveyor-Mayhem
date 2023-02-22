package me.carina.rpg.common.world;

public class WorldComponentSerializationData{
    WorldComponentData data;
    float x;
    float y;
    //RADIAN
    float rotation;
    public WorldComponentSerializationData(){} // for json loading
    public WorldComponentSerializationData(float x, float y, float rotation, WorldComponentData data){
        this.x = x;
        this.y = y;
        this.rotation = rotation;
        this.data = data;
    }
}
