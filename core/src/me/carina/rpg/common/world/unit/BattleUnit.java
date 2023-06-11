package me.carina.rpg.common.world.unit;

import me.carina.rpg.common.world.Feature;
import me.carina.rpg.common.file.AssetGroup;

public class BattleUnit extends BattleActor {



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
        public void initFeature(Feature feature) {
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
