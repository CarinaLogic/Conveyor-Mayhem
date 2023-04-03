package me.carina.rpg.common.map;

import me.carina.rpg.common.AbstractGameInstance;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.file.Identifier;

public class BattleUnit extends BattleActor {


    public BattleUnit(AbstractGameInstance game, Feature.Def def) {
        super(game, def);
    }

    public BattleUnit(AbstractGameInstance game, Identifier id) {
        super(game, id);
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
    public AssetGroup getAssetGroup() {
        return AssetGroup.units;
    }
    public static class Def extends Feature.Def{
        BattleActor.DirectionalParam top;
        BattleActor.DirectionalParam bottom;
        BattleActor.DirectionalParam left;
        BattleActor.DirectionalParam right;
        int hp;

        @Override
        public void init(Feature feature) {
            if (feature instanceof BattleUnit) {
                BattleUnit that = (BattleUnit) feature;
                that.top = top;
                that.bottom = bottom;
                that.left = left;
                that.right = right;
                that.hp = new FractionalParam(hp);
            }
        }
    }
}
