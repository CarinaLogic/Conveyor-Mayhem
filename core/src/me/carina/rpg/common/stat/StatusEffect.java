package me.carina.rpg.common.stat;

import me.carina.rpg.Game;
import me.carina.rpg.common.Definition;
import me.carina.rpg.common.Feature;
import me.carina.rpg.common.file.AssetGroup;

public class StatusEffect extends Feature {
    int duration;

    @Override
    public Class<? extends Definition> getDefClass() {
        return null;
    }

    @Override
    public AssetGroup getAssetGroup() {
        return null;
    }

    @Override
    public void tick() {
        if (duration == -1) return;
        if (duration == 0){
            Game.getInstance().getContext().get(StatusEffects.class).remove(this);
        }
    }
}
