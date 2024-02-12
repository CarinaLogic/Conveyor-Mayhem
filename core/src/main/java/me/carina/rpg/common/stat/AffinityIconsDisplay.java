package me.carina.rpg.common.stat;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ObjectMap;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.MapDisplayHandler;

public class AffinityIconsDisplay extends Table implements Display<AffinityCounter> {
    AffinityCounter counter;
    MapDisplayHandler<Affinity,Integer> handler = new MapDisplayHandler<>(this,
        entry -> this.add(entry.key.newDisplay(AffinityIconsDisplay.class))) {
        @Override
        public Iterable<ObjectMap.Entry<Affinity, Integer>> getIterable() {
            return counter;
        }
    };
    @Override
    public AffinityCounter getFeature() {
        return counter;
    }
}
