package me.carina.rpg.common.faction;

import com.badlogic.gdx.graphics.Color;
import me.carina.rpg.common.ChildFeature;
import me.carina.rpg.common.Definition;
import me.carina.rpg.common.Feature;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.unit.Units;

import java.util.Objects;

public class Faction extends Feature {
    @ChildFeature
    Units units = new Units();
    String name = "";

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

    }

    public Units getUnits() {
        return units;
    }
}
