package me.carina.rpg.common.soul;

import me.carina.rpg.Game;
import me.carina.rpg.common.Context;
import me.carina.rpg.common.Feature;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.rng.RandomNumberGenerator;
import me.carina.rpg.common.stat.Affinity;

public class Soul extends Feature {
    int strength;
    Affinity affinity;
    public Soul(){} //for json
    public void generateRandom(){
        this.strength = Game.getInstance().getContext().get(RandomNumberGenerator.class).getUntil(20);
        this.affinity = new Affinity(Game.getInstance().getContext().get(RandomNumberGenerator.class).getEnum(Affinity.AffinityType.class));
    }

    @Override
    public AssetGroup getAssetGroup() {
        return null;
    }

    @Override
    public void tick() {

    }
}
