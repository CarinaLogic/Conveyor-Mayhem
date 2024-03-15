package me.carina.rpg.client.actions;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import me.carina.rpg.Game;
import me.carina.rpg.common.util.Array;

public class UISizeToAction extends TemporalAction implements ResizeAction{
    float startWidth, startHeight;
    float targetWidth, targetHeight;

    @Override
    protected void begin() {
        Array<Action> toRemove = new Array<>();
        for (Action action : target.getActions()) {
            if (action == this) continue;
            if (action instanceof ResizeAction){
                if (!MathUtils.isEqual(((ResizeAction)action).getTargetWidth(),getTargetWidth())
                    || !MathUtils.isEqual(((ResizeAction)action).getTargetWidth(),getTargetWidth())) {
                    toRemove.add(action);
                }
                else {
                    toRemove.add(this);
                }
            }
        }
        for (Action action : toRemove) {
            target.removeAction(action);
        }
        if (toRemove.contains(this,true)){
            // update() will be called once right after even if you remove it from list
            // those with width|height of -1 will be ignored upon update()
            startWidth = -1;
            startHeight = -1;
            return;
        }
        Group parent = target.getParent();
        if (parent instanceof Table) {
            Table table = (Table) parent;
            Cell<?> cell = table.getCell(target);
            startWidth = cell.getPrefWidth();
            startHeight = cell.getPrefHeight();
        }
    }

    @Override
    protected void end() {
        Group parent = target.getParent();
        if (parent instanceof Table) {
            Table table = (Table) parent;
            Cell<?> cell = table.getCell(target);
            cell.size(targetWidth,targetHeight);
            table.invalidateHierarchy();
        }
    }

    @Override
    protected void update(float percent) {
        if (startWidth == -1 || startHeight == -1){
            return;
        }
        Group parent = target.getParent();
        if (parent instanceof Table) {
            Table table = (Table) parent;
            Cell<?> cell = table.getCell(target);
            cell.size(startWidth+(targetWidth-startWidth)*percent,startHeight+(targetHeight-startHeight)*percent);
            table.invalidateHierarchy();
        }
    }

    public void setTargetWidth(float targetWidth) {
        this.targetWidth = targetWidth;
    }

    public void setTargetHeight(float targetHeight) {
        this.targetHeight = targetHeight;
    }

    @Override
    public float getTargetWidth() {
        return targetWidth;
    }

    @Override
    public float getTargetHeight() {
        return targetHeight;
    }
}
