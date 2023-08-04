package me.carina.rpg.common.unit;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import me.carina.rpg.Game;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.Feature;
import me.carina.rpg.common.FlatImageDisplay;

public class UnitPartDisplay extends FlatImageDisplay {
    UnitPart unitPart;
    TextureRegionDrawable drawable;
    public UnitPartDisplay(UnitPart unitPart){
        this.unitPart = unitPart;
    }

    @Override
    public void tick() {
        if (drawable == null){
            //very inefficient
            TextureRegion region = Game.getClient().getAssets().get(unitPart.getPath(), TextureRegion.class);
            TextureData data = region.getTexture().getTextureData();
            if (data.isPrepared()) data.prepare();
            Pixmap textureMap = data.consumePixmap();
            Pixmap pixmap = new Pixmap(region.getRegionWidth(), region.getRegionHeight(), Pixmap.Format.RGBA8888);
            pixmap.setBlending(Pixmap.Blending.None);
            pixmap.drawPixmap(textureMap,0,0,region.getRegionX(), region.getRegionY(), region.getRegionWidth(), region.getRegionHeight());
            Texture texture = new Texture(pixmap);
            drawable = new TextureRegionDrawable(texture);
            pixmap.dispose();
            textureMap.dispose();
        }
    }

    @Override
    public float getDisplayX() {
        return 0;
    }

    @Override
    public float getDisplayY() {
        return 0;
    }

    @Override
    public float getDisplayWidth() {
        return 1;
    }

    @Override
    public float getDisplayHeight() {
        return 1;
    }

    @Override
    public Feature getFeature() {
        return unitPart;
    }

    @Override
    public boolean fillChildren() {
        return true;
    }

    @Override
    public Drawable getDrawable() {
        return drawable;
    }
}
