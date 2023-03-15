package me.carina.rpg.common.map;

import com.badlogic.gdx.scenes.scene2d.Actor;
import me.carina.rpg.common.AbstractGameInstance;

public class Floor extends AbstractFeature{
    String id;
    boolean passable;

    public Floor(AbstractGameInstance game, String id, boolean passable) {
        super(game);
        this.id = id;
        this.passable = passable;
    }

}
