package me.carina.rpg.common.map;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import me.carina.rpg.client.Client;
import me.carina.rpg.common.AbstractGameInstance;

public class BattleUnitDef extends AbstractFeatureDef<BattleUnit>{
    String id;
    AbstractBattleActor.DirectionalParam top;
    AbstractBattleActor.DirectionalParam bottom;
    AbstractBattleActor.DirectionalParam left;
    AbstractBattleActor.DirectionalParam right;
    int hp;

    @Override
    public BattleUnit toFeature(AbstractGameInstance game) {
        BattleUnit battleUnit = new BattleUnit(game);
        battleUnit.top = top;
        battleUnit.bottom = bottom;
        battleUnit.left = left;
        battleUnit.right = right;
        battleUnit.id = id;
        battleUnit.hp = new AbstractBattleActor.FractionalParam(hp);
        return battleUnit;
    }

    @Override
    public BattleUnitActor toActor(Client game) {
        return new BattleUnitActor(toFeature(game));
    }
}
