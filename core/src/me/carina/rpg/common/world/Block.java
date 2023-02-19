package me.carina.rpg.common.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;

public class Block extends WorldComponent{
    public static Block fromId(String id, AbstractGameInstance game){
        return WorldComponent.fromId("block/"+id, Block.class, game);
    }

}
