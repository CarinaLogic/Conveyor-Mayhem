package me.carina.rpg.client.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.utils.Align;
import me.carina.rpg.common.block.Direction;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public class UIAnimatedContainer<T extends Actor & Layout> extends Container<T> {
    BooleanSupplier displaySupplier = () -> false;
    Supplier<Direction> directionSupplier = () -> Direction.none;
    Supplier<T> actorSupplier = () -> null;
    public UIAnimatedContainer(){
        hide();
    }
    public UIAnimatedContainer<T> hide(){
        setScale(0);
        setVisible(false);
        return this;
    }
    public UIAnimatedContainer<T> show(){
        setScale(1);
        setVisible(true);
        return this;
    }
    public UIAnimatedContainer<T> supplyDisplay(BooleanSupplier supplier){
        this.displaySupplier = supplier;
        return this;
    }
    //Direction that the Actor is coming FROM, not velocity
    public UIAnimatedContainer<T> supplyDirection(Supplier<Direction> supplier){
        this.directionSupplier = supplier;
        return this;
    }
    public UIAnimatedContainer<T> supplyActor(Supplier<T> supplier){
        this.actorSupplier = supplier;
        return this;
    }

    @Override
    public void act(float delta) {
        T actor = getActor();
        T newActor;
        try {
            newActor = actorSupplier.get();
        } catch (Exception e){
            newActor = null;
        }
        //if new actor is null, hide, don't execute act() on child
        if (newActor == null){
            hide();
            setActor(null);
        }
        //if actor was changed to another non-null object, animate
        else if (actor != newActor){
            show();
            Direction d = directionSupplier.get();
            if (d == Direction.top) addFromScreenTop(newActor);
            if (d == Direction.bottom) addFromScreenBottom(newActor);
            if (d == Direction.left) addFromScreenLeft(newActor);
            if (d == Direction.right) addFromScreenRight(newActor);
            if (d == Direction.none) setActor(newActor);
        }
        if (isVisible()) {
            super.act(delta);
        }
    }

    public T unpack(){
        return getActor();
    }

    public UIAnimatedContainer<T> addFromScreenTop(T actor){
        setActor(actor);
        actor.clearActions();
        actor.setPosition(0,screenToLocalCoordinates(new Vector2(
                0,0
        )).y, Align.bottomLeft);
        actor.addAction(Actions.moveTo(0,0,0.2f));
        return this;
    }
    public UIAnimatedContainer<T> addFromScreenBottom(T actor){
        setActor(actor);
        actor.clearActions();
        actor.setPosition(0,screenToLocalCoordinates(new Vector2(
                0, Gdx.graphics.getHeight()
        )).y,Align.topLeft);
        actor.addAction(Actions.moveTo(0,0,0.2f));
        return this;
    }
    public UIAnimatedContainer<T> addFromScreenLeft(T actor){
        setActor(actor);
        actor.clearActions();
        actor.setPosition(screenToLocalCoordinates(new Vector2(
                0,0
        )).x,0,Align.bottomRight);
        actor.addAction(Actions.moveTo(0,0,0.2f));
        return this;
    }
    public UIAnimatedContainer<T> addFromScreenRight(T actor){
        setActor(actor);
        actor.clearActions();
        actor.setPosition(screenToLocalCoordinates(new Vector2(
                Gdx.graphics.getWidth(),0
        )).x,0,Align.bottomLeft);
        actor.addAction(Actions.moveTo(0,0,0.2f));
        return this;
    }
    public enum Direction{
        top, bottom, left, right, none;
    }

    @Override
    public void layout() {
        Actor actor = getActor();
        if (actor == null) return;
        float x = actor.getX();
        float y = actor.getY();
        super.layout();
        actor.setPosition(x,y);
    }
}
