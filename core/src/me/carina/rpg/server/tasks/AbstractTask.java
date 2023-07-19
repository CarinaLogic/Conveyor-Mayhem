package me.carina.rpg.server.tasks;

import com.badlogic.gdx.utils.Array;
import me.carina.rpg.Game;
import me.carina.rpg.server.Server;

public abstract class AbstractTask {
    Array<AbstractTask> nextTasks = new Array<>();
    boolean requireNextTask = false;
    boolean timed = false;
    boolean prioritized = false;
    float accumulatedTime = -1;
    float step = 1/60f;
    public AbstractTask(){}
    public void timed(){
        timed = true;
    }
    public void requireNextTask(){
        requireNextTask = true;
    }
    public void prioritize(){
        prioritized = true;
    }
    public void nextTasks(AbstractTask... tasks){
        nextTasks.addAll(tasks);
    }
    public void tick(float delta){
        if (accumulatedTime == -1) accumulatedTime = 0;
        if (timed){
            float frameTime = Math.min(delta, 0.25f);
            accumulatedTime += frameTime;
            while (accumulatedTime >= step) {
                if (run() && (!requireNextTask || !nextTasks.isEmpty())) terminate();
                accumulatedTime -= step;
            }
            return;
        }
        if (run() && (!requireNextTask || !nextTasks.isEmpty())) terminate();
    }

    /**
     * @return true if task should be terminated, false otherwise
     */
    public abstract boolean run();

    public void terminate(){
        Game.getServer().removeTask(this);
        for (AbstractTask nextTask : nextTasks) {
            Game.getServer().addTask(nextTask);
        }
    }

    public boolean isPrioritized() {
        return prioritized;
    }
}
