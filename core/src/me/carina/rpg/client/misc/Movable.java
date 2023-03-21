package me.carina.rpg.client.misc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

public class Movable extends ActorGestureListener{
    Vector2 prevInitPointer1 = new Vector2();
    Vector2 prevInitPointer2 = new Vector2();
    Vector2 prevPointer1 = new Vector2();
    Vector2 prevPointer2 = new Vector2();
    public Movable(){
        super(0.5f,0.4f,1f,Integer.MAX_VALUE);
    }

    @Override
    public boolean handle(Event e) {
        if (e instanceof InputEvent) {
            InputEvent inputEvent = (InputEvent) e;
            if (inputEvent.getType() == InputEvent.Type.scrolled){
                float amount = inputEvent.getScrollAmountY();
                Actor actor = inputEvent.getListenerActor();
                if (amount > 0 && actor.getScaleX() > 0.1 && actor.getScaleY() > 0.1) {
                    actor.setScale(actor.getScaleX()*0.875f, actor.getScaleY()*0.875f);
                }
                if (amount < 0 && actor.getScaleX() < 10 && actor.getScaleY() < 10){
                    actor.setScale(actor.getScaleX()/0.875f,actor.getScaleY()/0.875f);
                }
                return true;
            }
        }
        return super.handle(e);
    }

    @Override
    public void pan(InputEvent event, float x, float y, float deltaX, float deltaY) {
        Actor actor = event.getListenerActor();
        Vector2 v = actor.localToStageCoordinates(new Vector2(deltaX,deltaY));
        v.sub(actor.localToStageCoordinates(Vector2.Zero.cpy()));
        actor.addAction(Actions.moveBy(v.x,v.y));
    }

    @Override
    public void pinch(InputEvent event, Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        if (!initialPointer1.epsilonEquals(prevInitPointer1) || !initialPointer2.epsilonEquals(prevInitPointer2)){
            prevInitPointer1.set(initialPointer1);
            prevInitPointer2.set(initialPointer2);
            prevPointer1.set(pointer1);
            prevPointer2.set(pointer2);
            return;
        }
        Actor actor = event.getListenerActor();
        Vector2 v1 = pointer1.cpy().sub(pointer2);
        Vector2 v2 = prevPointer1.cpy().sub(prevPointer2);
        float scale = v1.len() / v2.len();
        Vector2 v3 = pointer1.cpy().add(pointer2).scl(0.5f);
        Vector2 v4 = prevPointer1.cpy().add(prevPointer2).scl(0.5f);
        v4.add(v3.scl(-scale));
        actor.localToStageCoordinates(v4);
        actor.setScale(actor.getScaleX()*scale, actor.getScaleY()*scale);
        actor.setPosition(v4.x,v4.y);
        prevPointer1.set(pointer1);
        prevPointer2.set(pointer2);
    }
}
