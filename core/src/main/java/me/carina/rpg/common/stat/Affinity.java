package me.carina.rpg.common.stat;

import me.carina.rpg.common.Feature;
import me.carina.rpg.common.file.AssetGroup;

public class Affinity extends Feature {
    AffinityType type;
    public Affinity(){} //for json
    public Affinity(AffinityType type){
        this.type = type;
    }
    @Override
    public AssetGroup getAssetGroup() {
        return null;
    }

    @Override
    public void tick() {

    }



    public enum AffinityType{
        fire,water
    }
}
