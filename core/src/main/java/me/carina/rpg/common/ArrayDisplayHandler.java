package me.carina.rpg.common;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.SnapshotArray;
import me.carina.rpg.Game;

import java.util.Arrays;
import java.util.function.Consumer;

public abstract class ArrayDisplayHandler<T>{
    Group group;
    Consumer<T> addFunc;
    Consumer<Actor> removeFunc;
    public ArrayDisplayHandler(Group group, Consumer<T> addFunc){
        this(group,addFunc, Actor::remove);
    }
    public ArrayDisplayHandler(Group group, Consumer<T> addFunc, Consumer<Actor> removeFunc){
        this.group = group;
        this.addFunc = addFunc;
        this.removeFunc = removeFunc;
    }
    public abstract Iterable<T> getIterable();
    public void tick() {
        Iterable<T> iterable;
        try {
            iterable = getIterable();
        } catch (Exception ignored){
            return;
        }
        if (iterable == null) return;
        SnapshotArray<Actor> children = group.getChildren();
        boolean[] checkList = new boolean[children.size];
        Arrays.fill(checkList, false);
        for (T feature : getIterable()) {
            boolean success = false;
            for (Actor display : Game.getClient().getDisplays().getAll((Feature) feature)) {
                int i = children.indexOf(display,true);
                if (i != -1){
                    checkList[i] = true;
                    success = true;
                    break;
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
