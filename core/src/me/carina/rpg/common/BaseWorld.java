package me.carina.rpg.common;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public abstract class BaseWorld {
    World world = new World(new Vector2(0,-10),true);

}
