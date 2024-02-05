package me.carina.rpg.client.scenes;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import me.carina.rpg.Game;
import me.carina.rpg.client.misc.CursorListener;
import me.carina.rpg.client.ui.CursorHandler;
import me.carina.rpg.client.ui.CursorPositionHolder;
import me.carina.rpg.common.util.Array;

import java.util.Comparator;
import java.util.function.Function;

public abstract class GameStage<T extends BaseScreen> extends Stage implements CursorHandler{
    T screen;

    public GameStage(Viewport viewport){
        super(viewport);
        addListener(newListener());
        setDebugAll(true);
    }

    @Override
    public void act(float delta) {
        Game.getClient().getContext().add(this);
        super.act(delta);
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
                return recursiveRun(CursorHandler::goLeft);
            }

            @Override
            public boolean right(InputEvent event) {
                return recursiveRun(CursorHandler::goRight);
            }

            @Override
            public boolean up(InputEvent event) {
                return recursiveRun(CursorHandler::goUp);
            }

            @Override
            public boolean down(InputEvent event) {
                return recursiveRun(CursorHandler::goDown);
            }

            @Override
            public boolean enter(InputEvent event) {
                return recursiveRun(CursorHandler::enter);
            }

            @Override
            public boolean exit(InputEvent event) {
                return recursiveRun(CursorHandler::exit);
            }
        };
    }

    public boolean recursiveRun(Function<CursorHandler,Boolean> func){
        Array<CursorHandler> handlers = new Array<>();
        handlers.add(this);
        recursiveRun(getRoot(),handlers);
        handlers.sort(Comparator.comparingInt(CursorHandler::priority));
        handlers.reverse();
        for (CursorHandler handler : handlers) {
            if (func.apply(handler)) return true;
        }
        return false;
    }

    public void recursiveRun(Actor actor, Array<CursorHandler> candidates){
        if (actor == null) return;
        if (actor instanceof CursorHandler) {
            CursorHandler handler = (CursorHandler) actor;
            candidates.add(handler);
        }
        if (actor instanceof Group) {
            Group group = (Group) actor;
            if (group instanceof CursorPositionHolder) {
                CursorPositionHolder holder = (CursorPositionHolder) group;
                recursiveRun(holder.getSelected(),candidates);
            }
            else {
                for (Actor child : group.getChildren()) {
                    recursiveRun(child,candidates);
                }
            }
        }
    }
}
