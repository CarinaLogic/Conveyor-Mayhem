package me.carina.rpg.client.actions;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import me.carina.rpg.common.util.Array;

public class UISizeByAction extends TemporalAction implements ResizeAction{
    float startWidth, startHeight;
    float widthDelta, heightDelta;

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
            cell.size(startWidth+widthDelta,startHeight+heightDelta);
            table.invalidateHierarchy();
        }
    }

    @Override
    protected void update(float percent) {
        Group parent = target.getParent();
        if (parent instanceof Table) {
            Table table = (Table) parent;
            Cell<?> cell = table.getCell(target);
            cell.size(startWidth+widthDelta*percent,startHeight+heightDelta*percent);
            table.invalidateHierarchy();
        }
    }

    public void setHeightDelta(float heightDelta) {
        this.heightDelta = heightDelta;
    }

    public void setWidthDelta(float widthDelta) {
        this.widthDelta = widthDelta;
    }

    @Override
    public float getTargetWidth() {
        return startWidth+widthDelta;
    }

    @Override
    public float getTargetHeight() {
        return startHeight+heightDelta;
    }
}
