package me.carina.rpg.client.scenes;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.viewport.Viewport;
import me.carina.rpg.client.misc.CursorListener;
import me.carina.rpg.client.ui.CursorHandler;
import me.carina.rpg.client.ui.CursorPositionHolder;
import me.carina.rpg.client.ui.Selectable;

import java.util.function.Consumer;
import java.util.function.Function;

public abstract class GameStage<T extends BaseScreen> extends Stage{
    T screen;
    
    public GameStage(Viewport viewport){
        super(viewport);
        addListener(newListener());
    }
    public GameStage(Viewport viewport, Batch batch){
        super(viewport, batch);
        addListener(newListener());
    }
    public abstract void init();

    public T getScreen() {
        return screen;
    }

    public void setScreen(T screen) {
        this.screen = screen;
    }

    public CursorListener newListener(){
        //Cursor cannot be handled by normal event propagation cuz multiple actors that might not be on the same branch as keyboardFocus can handle the event
        return new CursorListener(){
            @Override
            public boolean left(InputEvent event) {
                return recursiveRun(getRoot(), CursorHandler::goLeft);
            }

            @Override
            public boolean right(InputEvent event) {
                return recursiveRun(getRoot(), CursorHandler::goRight);
            }

            @Override
            public boolean up(InputEvent event) {
                return recursiveRun(getRoot(), CursorHandler::goUp);
            }

            @Override
            public boolean down(InputEvent event) {
                 return recursiveRun(getRoot(), CursorHandler::goDown);
            }

            @Override
            public boolean enter(InputEvent event) {
                return recursiveRun(getRoot(), CursorHandler::enter);
            }

            @Override
            public boolean exit(InputEvent event) {
                return recursiveRun(getRoot(), CursorHandler::exit);
            }
        };
    }

    public boolean recursiveRun(Actor actor, Function<CursorHandler,Boolean> func){
        boolean retValue = false;
        if (actor == null) return false;
        if (actor instanceof CursorHandler) {
            CursorHandler handler = (CursorHandler) actor;
            retValue = func.apply(handler);
        }
        if (actor instanceof Group) {
            Group group = (Group) actor;
            if (group instanceof CursorPositionHolder) {
                CursorPositionHolder holder = (CursorPositionHolder) group;
                recursiveRun(holder.getSelected(),func);
            }
            else {
                for (Actor child : group.getChildren()) {
                    retValue = retValue || recursiveRun(child, func);
                }
            }
        }
        return retValue;
    }
}
