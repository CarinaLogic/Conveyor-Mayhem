package me.carina.conveyor.server.tasks;

import me.carina.conveyor.common.battle.BattleMap;

public class BattleMapUpdateTask extends AbstractTask{
    BattleMap map;
    public BattleMapUpdateTask(BattleMap map){
        this.map = map;
    }

    @Override
    public boolean run() {
        map.contextAndTick();
        return false;
    }
}
