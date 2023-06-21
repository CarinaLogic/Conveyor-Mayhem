package me.carina.rpg.client.actions;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import me.carina.rpg.common.util.Array;

public class MultiTargetAction extends Action {
    Array<Actor> targets = new Array<>();
    Action action;
    public MultiTargetAction(Action action, Actor... targets){
        this.action = action;
        this.targets.addAll(targets);
    }
    @Override
    public boolean act(float delta) {
        if (targets.isEmpty()) return true;
        for (int i = 0; i < targets.size; i++) {
            action.setTarget(targets.get(i));
            if (i == targets.size - 1){
                if (action.act(delta)) targets.removeIndex(i);
            }
            else if (action.act(0)) targets.removeIndex(i);
        }
        return targets.isEmpty();
    }
}
