package me.carina.rpg.common.map;

import me.carina.rpg.common.AbstractGameInstance;

public class BattleUnit extends AbstractBattleActor{

    public BattleUnit(AbstractGameInstance game) {
        super(game);
    }

    @Override
    public BattleUnitActor newActor() {
        return new BattleUnitActor(this);
    }
}
