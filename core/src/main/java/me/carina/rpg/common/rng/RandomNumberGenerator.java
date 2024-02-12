package me.carina.rpg.common.rng;

import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import me.carina.rpg.common.Feature;
import me.carina.rpg.common.file.AssetGroup;

public class RandomNumberGenerator extends Feature {
    RandomXS128 random;
    public RandomNumberGenerator(){
        random = new RandomXS128();
    }
    public int getUntil(int max){
        return random.nextInt(max);
    }
    public <T extends Enum<?>> T getEnum(Class<T> cls) {
        if (ClassReflection.isEnum(cls)) return null;
        Object[] constants = ClassReflection.getEnumConstants(cls);
        if (constants.length == 0) return null;
        //noinspection unchecked
        return (T) constants[this.getUntil(constants.length)];
    }


    @Override
    public AssetGroup getAssetGroup() {
        return null;
    }

    @Override
    public void tick() {
        //How to combat rng abusing #1, advance the rng every tick
        random.nextInt();
    }
}
