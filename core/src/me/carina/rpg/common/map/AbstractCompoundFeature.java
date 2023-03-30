package me.carina.rpg.common.map;

import com.badlogic.gdx.scenes.scene2d.Actor;
import me.carina.rpg.common.AbstractGameInstance;

public abstract class AbstractCompoundFeature extends AbstractFeature<AbstractCompoundFeature.Def>{
    public AbstractCompoundFeature(AbstractGameInstance game) {
        super(game, new AbstractCompoundFeature.Def());
    }

    @Override
    public void init(Def def) {
        //NOOP
    }

    @Override
    public Class<Def> getDefClass() {
        return Def.class;
    }

    @Override
    public String getTypePrefix() {
        return "";
    }

    public static class Def extends AbstractFeature.Def{}
}
