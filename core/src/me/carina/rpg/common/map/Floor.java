package me.carina.rpg.common.map;

import com.badlogic.gdx.scenes.scene2d.Actor;
import me.carina.rpg.common.AbstractGameInstance;

public class Floor extends AbstractFeature<Floor.Def>{
    String id;
    boolean passable;

    public Floor(AbstractGameInstance game, String id) {
        super(game, id);
    }

    public Floor(AbstractGameInstance game, Def def) {
        super(game, def);
    }


    @Override
    public void init(Def def) {
        id = def.id;
        passable = def.passable;
    }

    @Override
    public FloorActor newActor() {
        return new FloorActor(this);
    }

    @Override
    public Class<Def> getDefClass() {
        return Def.class;
    }

    @Override
    public String getTypePrefix() {
        return "floors";
    }

    public static class Def extends AbstractFeature.Def{
        String id;
        boolean passable;
    }
}
