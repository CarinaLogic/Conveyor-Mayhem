package me.carina.rpg.client.actions;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class UISizeToAction extends TemporalAction {
    float startWidth, startHeight;
    float targetWidth, targetHeight;

    @Override
    protected void begin() {
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
}
