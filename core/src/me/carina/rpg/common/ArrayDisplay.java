package me.carina.rpg.common;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.SnapshotArray;

import java.util.Arrays;
import java.util.function.Consumer;

public class ArrayDisplay<T extends Feature>{
    Iterable<T> iterable;
    Group group;
    Consumer<Feature> addFunc;
    Consumer<Actor> removeFunc;
    public ArrayDisplay(Iterable<T> feature, Group group, Consumer<Feature> addFunc){
        this(feature,group,addFunc, Actor::remove);
    }
    public ArrayDisplay(Iterable<T> feature, Group group, Consumer<Feature> addFunc, Consumer<Actor> removeFunc){
        this.iterable = feature;
        this.group = group;
        this.addFunc = addFunc;
        this.removeFunc = removeFunc;
    }
    public void tick() {
        SnapshotArray<Actor> children = group.getChildren();
        boolean[] checkList = new boolean[children.size];
        Arrays.fill(checkList, false);
        for (T feature : iterable) {
            boolean success = false;
            for (Actor display : feature.getDisplays()) {
                int i = children.indexOf(display,true);
                if (i != -1){
                    checkList[i] = true;
                    success = true;
                }
            }
            if (!success){
                //there's no children corresponds to the arrayFeature array, add it to displays
                addFunc.accept(feature);
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
