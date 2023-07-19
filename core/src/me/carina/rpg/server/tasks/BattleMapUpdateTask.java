package me.carina.rpg.server.tasks;

import me.carina.rpg.common.Context;
import me.carina.rpg.common.battle.BattleMap;
import me.carina.rpg.server.Server;

public class BattleMapUpdateTask extends AbstractTask{
    BattleMap map;
    public BattleMapUpdateTask(BattleMap map){
        this.map = map;
    }

    @Override
    public boolean run() {
        map.tick(new Context());
        return false;
    }
}
