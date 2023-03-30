package me.carina.rpg.common.map;

import me.carina.rpg.common.AbstractGameInstance;

public class BattleUnit extends AbstractBattleActor<BattleUnit.Def>{


    public BattleUnit(AbstractGameInstance game, String id) {
        super(game, id);
    }

    public BattleUnit(AbstractGameInstance game, Def def) {
        super(game, def);
    }

    @Override
    public void init(Def def) {
        id = def.id;
        top = def.top;
        bottom = def.bottom;
        left = def.left;
        right = def.right;
        hp = new FractionalParam(def.hp);
    }

    @Override
    public BattleUnitActor newActor() {
        return new BattleUnitActor(this);
    }

    @Override
    public Class<Def> getDefClass() {
        return Def.class;
    }

    @Override
    public String getTypePrefix() {
        return "units";
    }
    public static class Def extends AbstractFeature.Def{
        String id;
        AbstractBattleActor.DirectionalParam top;
        AbstractBattleActor.DirectionalParam bottom;
        AbstractBattleActor.DirectionalParam left;
        AbstractBattleActor.DirectionalParam right;
        int hp;
    }
}
