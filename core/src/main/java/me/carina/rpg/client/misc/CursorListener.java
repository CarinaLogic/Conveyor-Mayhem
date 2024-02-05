package me.carina.rpg.client.misc;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import me.carina.rpg.client.ui.Selectable;
import me.carina.rpg.common.util.Array;

public class CursorListener extends InputListener {
    @Override
    public boolean keyDown(InputEvent event, int keycode) {
        if (keycode == Input.Keys.H) return enter(event);
        else if (keycode == Input.Keys.G) return exit(event);
        else return false;
    }

    @Override
    public boolean keyTyped(InputEvent event, char character) {
        if (character == 'w') return up(event);
        else if (character == 's') return down(event);
        else if (character == 'a') return left(event);
        else if (character == 'd')return right(event);
        else return false;
    }

    public boolean enter(InputEvent event){
        return false;
    }

    public boolean exit(InputEvent event){
        return false;
    }

    public boolean up(InputEvent event){
        Group root = event.getStage().getRoot();
        Actor actor = event.getTarget();
        float x = actor.getX(Align.top);
        float y = actor.getY(Align.top);
        Actor bestCandidate = actor;
        float bestScore = 10000000;
        for (Actor child : getAllSelectableChildren(root)) {
            float dx = child.getX(Align.bottom);
            float dy = child.getY(Align.bottom);
            float score = 0;
            if (y > dy) {
                score += 100000;
            }
            score += Math.abs(x - dx);
            score += 5 * (dy - y);
            if (bestScore > score){
                bestScore = score;
                bestCandidate = child;
            }
        }
        actor.getStage().setKeyboardFocus(bestCandidate);
        return true;
    }
    public boolean down(InputEvent event){
        Group root = event.getStage().getRoot();
        Actor actor = event.getTarget();
        float x = actor.getX(Align.bottom);
        float y = actor.getY(Align.bottom);
        Actor bestCandidate = actor;
        float bestScore = 10000000;
        for (Actor child : getAllSelectableChildren(root)) {
            float dx = child.getX(Align.top);
            float dy = child.getY(Align.top);
            float score = 0;
            if (y < dy) {
                score += 100000;
            }
            score += Math.abs(x - dx);
            score += 5 * (y - dy);
            if (bestScore > score){
                bestScore = score;
                bestCandidate = child;
            }
        }
        actor.getStage().setKeyboardFocus(bestCandidate);
        return true;
    }
    public boolean left(InputEvent event){
        Group root = event.getStage().getRoot();
        Actor actor = event.getTarget();
        float x = actor.getX(Align.left);
        float y = actor.getY(Align.left);
        Actor bestCandidate = actor;
        float bestScore = 10000000;
        for (Actor child : getAllSelectableChildren(root)) {
            float dx = child.getX(Align.right);
            float dy = child.getY(Align.right);
            float score = 0;
            if (x < dx) {
                score += 100000;
            }
            score += Math.abs(y - dy);
            score += 5 * (x - dx);
            if (bestScore > score){
                bestScore = score;
                bestCandidate = child;
            }
        }
        actor.getStage().setKeyboardFocus(bestCandidate);
        return true;
    }
    public boolean right(InputEvent event){
        Group root = event.getStage().getRoot();
        Actor actor = event.getTarget();
        float x = actor.getX(Align.right);
        float y = actor.getY(Align.right);
        Actor bestCandidate = actor;
        float bestScore = 10000000;
        for (Actor child : getAllSelectableChildren(root)) {
            float dx = child.getX(Align.left);
            float dy = child.getY(Align.left);
            float score = 0;
            if (x > dx) {
                score += 100000;
            }
            score += Math.abs(dy - y);
            score += 5 * (x - dx);
            if (bestScore > score){
                bestScore = score;
                bestCandidate = child;
            }
        }
        actor.getStage().setKeyboardFocus(bestCandidate);
        return true;
    }
    public static Array<Actor> getAllSelectableChildren(Actor actor){
        Array<Actor> array = new Array<>();
        if (ClassReflection.getDeclaredAnnotation(actor.getClass(), Selectable.class) != null){
            array.add(actor);
        }
        if (actor instanceof Group) {
            Group group = (Group) actor;
            for (Actor child : group.getChildren()) {
                array.addAll(getAllSelectableChildren(child));
            }
        }
        return array;
    }
}
