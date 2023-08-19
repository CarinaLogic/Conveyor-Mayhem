package me.carina.rpg.client.misc;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import me.carina.rpg.Game;

public class CursorListener extends InputListener {
    //make KeyboardFocus = the item selected?

    @Override
    public boolean keyTyped(InputEvent event, char character) {
        if (character == 'w') keyUp(event);
        if (character == 's') keyDown(event);
        if (character == 'a') keyLeft(event);
        if (character == 'd') keyRight(event);
        return true;
    }

    public void keyUp(InputEvent event){
        Actor actor = event.getTarget();
        Group parent = actor.getParent();
        if (parent instanceof Table) {
            Table table = (Table) parent;
            Cell<Actor> cell = table.getCell(actor);
            if (cell == null) return;
            int col = cell.getColumn();
            int row = cell.getRow();
            Actor bestTarget = actor;
            int bestScore = 999;
            for (Cell<?> cellToCheck : table.getCells()) {
                int score = 0;
                if (cellToCheck.getRow() < row){
                    score += row - cellToCheck.getRow();
                }
                else {
                    score += row + 1;
                    score += table.getRows() - cellToCheck.getRow();
                }
                score += Math.abs(col - cellToCheck.getColumn());
                if (bestScore > score){
                    bestScore = score;
                    bestTarget = cellToCheck.getActor();
                }
            }
            if (bestTarget instanceof Table) {
                Table targetTable = (Table) bestTarget;
                 targetTable.getCells()
            }
            event.getStage().setKeyboardFocus(bestTarget);
        }
    }
    public void keyDown(InputEvent event){

    }
    public void keyLeft(InputEvent event){

    }
    public void keyRight(InputEvent event){

    }
    public Actor getBestActor(InputEvent event)
}
