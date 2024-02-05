package me.carina.rpg.common.rng;

import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.utils.LongQueue;
import com.badlogic.gdx.utils.Queue;
import me.carina.rpg.common.Feature;
import me.carina.rpg.common.file.AssetGroup;

import java.util.Random;

public class RandomNumberGenerator extends Feature {
    static long MASK = 0xc18f771905d3L;
    static long MULTIPLIER = 0xd6ca9689174eL;
    static long MASK2 = 0x495952b7117eL;
    long seed;
    long amplitude; //default=0 always_same=-1 always_max_or_min=1
    long frequency; //default=20
    long variation; //default=20 always_aligned_to_curve=0
    long timeOffset; //default=0 starts_at_max=0.5 start_downward=1 start_at_min=1.5
    long valueOffset; //default=0 mid_is_0=-50 mid_is_100=50
    long index;

    public long getNumber(long offset){
        //TODO
        return 0;
    }

    @Override
    public AssetGroup getAssetGroup() {
        return null;
    }

    @Override
    public void tick() {

    }
}
