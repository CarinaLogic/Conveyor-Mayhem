package me.carina.rpg.common;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.SnapshotArray;
import me.carina.rpg.common.util.Array2D;

import java.util.Arrays;

public abstract class Array2DDisplay<T extends Feature> extends Display {
    @Override
    public void tick() {
        SnapshotArray<Actor> children = getChildren();
        boolean[] checkList = new boolean[children.size];
        Arrays.fill(checkList, false);
        for (Array2D.Array2DEntry<T> entry : getFeature()) {
            int i = children.indexOf(entry.value.getDisplay(),true);
            if (i != -1){
                checkList[i] = true;
            }
            else {
                //there's no children corresponds to the feature array, add it to display
                addActor(entry.value.generateDisplay());
            }
        }
        for (int i = 0; i < checkList.length; i++) {
            if (!checkList[i]){
                //there's no array entry for this child, remove it
                removeActor(children.get(i));
            }
        }
        tickMore();
    }

    public abstract void tickMore();

    @Override
    public float getDisplayX() {
        return 0;
    }

    @Override
    public float getDisplayY() {
        return 0;
    }

    @Override
    public float getDisplayWidth() {
        return 0;
    }

    @Override
    public float getDisplayHeight() {
        return 0;
    }

    @Override
    public abstract Array2DFeature<T> getFeature();

}
