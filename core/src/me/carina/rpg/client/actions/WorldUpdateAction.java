package me.carina.rpg.client.actions;

import me.carina.rpg.common.world.WorldActor;

public class WorldUpdateAction extends TimedRepeatingAction {

    @Override
    public void act() {
        ((WorldActor) getTarget()).getWorld().update(step);
    }
}
