package me.carina.rpg.common.map;

import me.carina.rpg.common.AbstractGameInstance;

public class Floor extends Feature{
    String id;
    boolean passable;

    public Floor(AbstractGameInstance game, String id) {
        super(game, id);
    }

    public Floor(AbstractGameInstance game, Def def) {
        super(game, def);
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


    public static class Def extends Feature.Def{
        String id;
        boolean passable;

        @Override
        public void init(Feature feature) {
            if (feature instanceof Floor) {
                Floor that = (Floor) feature;
                that.id = id;
                that.passable = passable;
            }
        }
    }
}
