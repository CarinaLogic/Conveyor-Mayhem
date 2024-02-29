package me.carina.rpg.common.stat;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ObjectMap;
import me.carina.rpg.Game;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.MapDisplayHandler;

import java.util.function.Supplier;

public class AffinityIconsDisplay extends Table implements Display<AffinityCounter> {
    Supplier<AffinityCounter> counterSupplier;
    MapDisplayHandler<Affinity,Integer> handler = new MapDisplayHandler<>(this,
        entry -> this.add(entry.key.newDisplay(AffinityIconsDisplay.class))) {
        @Override
        public Iterable<ObjectMap.Entry<Affinity, Integer>> getIterable() {
            return getFeature();
        }
    };

    public AffinityIconsDisplay(Supplier<AffinityCounter> counterSupplier){
        this.counterSupplier = counterSupplier;
    }

    @Override
    public void act(float delta) {
        Game.getInstance().getContext().add(this);
        handler.tick();
        super.act(delta);
    }

    @Override
    public Supplier<AffinityCounter> getFeatureSupplier() {
        return counterSupplier;
    }
}
