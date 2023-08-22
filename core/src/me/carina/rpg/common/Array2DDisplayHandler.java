package me.carina.rpg.common;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.SnapshotArray;
import me.carina.rpg.common.util.Array2D;

import java.util.Arrays;
import java.util.function.Consumer;

public abstract class Array2DDisplayHandler {
    Group group;
    Consumer<Feature> addFunc;
    Consumer<Actor> removeFunc;
    public Array2DDisplayHandler(Group group, Consumer<Feature> addFunc){
        this(group,addFunc, Actor::remove);
    }
    public Array2DDisplayHandler(Group group, Consumer<Feature> addFunc, Consumer<Actor> removeFunc){
        this.group = group;
        this.addFunc = addFunc;
        this.removeFunc = removeFunc;
    }
    public abstract Array2DFeature<? extends Feature> getIterable();
    public void tick() {
        SnapshotArray<Actor> children = group.getChildren();
        boolean[] checkList = new boolean[children.size];
        Arrays.fill(checkList, false);
        for (Array2D.Array2DEntry<? extends Feature> entry : getIterable()) {
            boolean success = false;
            if (entry.value != null) {
                for (Actor display : entry.value.getDisplays()) {
                    int i = children.indexOf(display, true);
                    if (i != -1) {
                        checkList[i] = true;
                        success = true;
                    }
                }
                if (!success) {
                    //there's no children corresponds to the arrayFeature array, add it to displays
                    addFunc.accept(entry.value);
                }
            }
        }
        for (int i = 0; i < checkList.length; i++) {
            if (!checkList[i]){
                //there's no array entry for this child, remove it
                removeFunc.accept(children.get(i));
            }
        }
    }
}
