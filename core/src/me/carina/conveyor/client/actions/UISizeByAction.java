package me.carina.conveyor.client.actions;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class UISizeByAction extends TemporalAction {
    float startWidth, startHeight;
    float widthDelta, heightDelta;

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
}
