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
        return new CursorListener(){
            @Override
            public boolean left(InputEvent event) {
                if (recursiveRun(getRoot(), CursorHandler::left)) return true;
                else return super.left(event);
            }

            @Override
            public boolean right(InputEvent event) {
                if (recursiveRun(getRoot(), CursorHandler::right)) return true;
                else return super.right(event);
            }

            @Override
            public boolean up(InputEvent event) {
                if (recursiveRun(getRoot(), CursorHandler::up)) return true;
                else return super.up(event);
            }

            @Override
            public boolean down(InputEvent event) {
                if (recursiveRun(getRoot(), CursorHandler::down)) return true;
                else return super.down(event);
            }

            @Override
            public boolean enter(InputEvent event) {
                if (recursiveRun(getRoot(), CursorHandler::enter)) return true;
                else return super.enter(event);
            }

            @Override
            public boolean exit(InputEvent event) {
                if (recursiveRun(getRoot(), CursorHandler::exit)) return true;
                else return super.exit(event);
            }
        };
    }

    public boolean recursiveRun(Actor actor, Function<CursorHandler,Boolean> func){
        boolean retValue = false;
        if (actor instanceof CursorHandler) {
            CursorHandler handler = (CursorHandler) actor;
            retValue = func.apply(handler);
        }
        if (actor instanceof Group) {
            Group group = (Group) actor;
            for (Actor child : group.getChildren()) {
                retValue = retValue || recursiveRun(child,func);
            }
        }
        return retValue;
    }
}
