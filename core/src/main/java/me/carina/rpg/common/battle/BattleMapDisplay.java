package me.carina.rpg.common.battle;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import me.carina.rpg.Game;
import me.carina.rpg.client.misc.Movable;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.Displays;
import me.carina.rpg.common.faction.FactionsDisplay;

import java.util.function.Supplier;

public class BattleMapDisplay extends Stack implements Display<BattleMap> {
    Supplier<BattleMap> mapSupplier;
    public BattleMapDisplay(){
        addActor(Game.getClient().getDisplays().get(()-> mapSupplier.get().tiles, TilesDisplay.class));
        addActor(Game.getClient().getDisplays().get(()-> mapSupplier.get().factions, FactionsDisplay.class));
        setTouchable(Touchable.childrenOnly);
        addListener(new Movable());
    }

    @Override
    public void act(float delta) {
        Game.getInstance().getContext().add(getFeature());
        setSize(10,10);
        //setSize(mapSupplier.get().getWidth(), mapSupplier.get().getHeight());
        super.act(delta);
    }


    @Override
    public Supplier<BattleMap> getFeatureSupplier() {
        return mapSupplier;
    }

    @Override
    public void setFeatureSupplier(Supplier<BattleMap> supplier) {
        this.mapSupplier = supplier;
    }
}
