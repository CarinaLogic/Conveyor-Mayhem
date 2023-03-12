package me.carina.rpg.common.map;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public abstract class AbstractFeatureActor<T extends AbstractFeature> extends Actor {
    T feature;
}
