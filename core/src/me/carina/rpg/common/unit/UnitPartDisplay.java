package me.carina.rpg.common.unit;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import me.carina.rpg.Game;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.Feature;
import me.carina.rpg.common.FlatImageDisplay;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.file.Path;
import me.carina.rpg.common.util.Palette;

public class UnitPartDisplay extends FlatImageDisplay {
    UnitPart unitPart;
    TextureRegion[][] regions;
    Palette palette;
    int spriteWidth = 1;
    int spriteHeight = 1;
    public UnitPartDisplay(UnitPart unitPart){
        this.unitPart = unitPart;
    }

    @Override
    public void tick() {
        if (regions == null && !unitPart.bodyType.equals(BodyType.base)){
            //very inefficient
            TextureRegionDrawable regionDrawable = Game.getClient().getAssets().get(unitPart.getPath(), TextureRegionDrawable.class);
            TextureRegion region = regionDrawable.getRegion();
            TextureData data = region.getTexture().getTextureData();
            if (!data.isPrepared()) data.prepare();
            Pixmap textureMap = data.consumePixmap();
            Pixmap pixmap = new Pixmap(region.getRegionWidth(), region.getRegionHeight(), Pixmap.Format.RGBA8888);
            pixmap.setBlending(Pixmap.Blending.None);
            pixmap.drawPixmap(textureMap,0,0,region.getRegionX(), region.getRegionY(), region.getRegionWidth(), region.getRegionHeight());
            int paletteWidth = pixmap.getWidth() % 32;
            int paletteSrcX = pixmap.getWidth() - paletteWidth;
            spriteWidth = pixmap.getWidth() / 32;
            spriteHeight = pixmap.getHeight() / 32;
            UnitPart part  = getContext().get(UnitParts.class).getPart(BodyType.base);
            if (part.getDisplay() == null) return;
            Palette basePalette = ((UnitPartDisplay) part.getDisplay()).getPalette();
            if (basePalette != null){
                basePalette.recolor(pixmap);
            }
            if (paletteWidth != 0){
                Pixmap paletteMap = new Pixmap(paletteWidth,spriteHeight, Pixmap.Format.RGBA8888);
                paletteMap.drawPixmap(pixmap,0,0,paletteSrcX,0,paletteWidth,spriteHeight);
                palette = new Palette(paletteMap, unitPart.colorIndex);
                palette.recolor(pixmap);
                paletteMap.dispose();
            }
            Texture texture = new Texture(pixmap);
            TextureRegion region1 = new TextureRegion(texture,0,0,spriteWidth*32,spriteHeight*32);
            regions = region1.split(32,32);
            pixmap.dispose();
            //textureMap.dispose();
        }
    }

    public Palette getPalette() {
        return palette;
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
        int index = unitPart.property.ordinal();
        if (this.unitPart.bodyType.equals(BodyType.base)) return null;
        if (index > spriteWidth * spriteHeight) return null;
        return new TextureRegionDrawable(regions[index / spriteWidth][index % spriteWidth]);
    }
}
