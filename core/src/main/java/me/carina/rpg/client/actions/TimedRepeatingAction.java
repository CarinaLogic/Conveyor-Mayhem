package me.carina.rpg.client.actions;

import com.badlogic.gdx.scenes.scene2d.Action;

public abstract class TimedRepeatingAction extends Action {
    float accumulatedTime;
    float step = 1/60f;
    public TimedRepeatingAction() {
        accumulatedTime = 0;
    }
    public TimedRepeatingAction(float step){
        this();
        this.step = step;
    }

    public void setStep(float step) {
        this.step = step;
    }

    @Override
    public boolean act(float delta) {
        float frameTime = Math.min(delta, 0.25f);
        accumulatedTime += frameTime;
        while (accumulatedTime >= step) {
            act();
            accumulatedTime -= step;
        }
        return false;
    }
    public abstract void act();
}
