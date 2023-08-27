package me.carina.rpg.common.unit;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TransformDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import me.carina.rpg.Game;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.util.Palette;

public class UIUnitPartDisplay extends Image implements Display<UnitPart> {
    UnitPart unitPart;
    TextureRegion[][] regions;
    Palette palette;
    int spriteWidth = 1;
    int spriteHeight = 1;
    public UIUnitPartDisplay(UnitPart unitPart){
        this.unitPart = unitPart;
        setScaling(Scaling.fill);
        setAlign(Align.topLeft);
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        Game.getInstance().getContext().add(getFeature());
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
            UnitPart part  = Game.getInstance().getContext().get(UnitParts.class).getPart(BodyType.base);
            if (part.getDisplays().isEmpty()) return;
            Palette basePalette = part.getDisplay(UIUnitPartDisplay.class).getPalette();
            if (basePalette != null){
                basePalette.recolor(pixmap);
            }
            if (paletteWidth != 0){
                Pixmap paletteMap = new Pixmap(paletteWidth,region.getRegionHeight(), Pixmap.Format.RGBA8888);
                paletteMap.drawPixmap(pixmap,0,0,paletteSrcX,0,paletteWidth,region.getRegionHeight());
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
        setDrawable(getPrefDrawable());
        validate();
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        Drawable drawable = getDrawable();
        if (drawable == null) return;
        float x = getX();
        float y = getY();
        float w = getWidth();
        float h = getHeight();
        if (shouldFlip()){
            x += w;
            w = -w;
        }
        if (drawable instanceof TransformDrawable) {
            TransformDrawable transformDrawable = (TransformDrawable) drawable;
            transformDrawable.draw(batch,x+getImageX(),y+getImageY(),getOriginX()-getImageX(),getOriginY()-getImageY(),
                    getImageWidth(),getImageHeight(),getScaleX(),getScaleY(),getRotation());
            return;
        }
        drawable.draw(batch,x+getImageY(),y+getImageY(),getImageWidth()*getScaleX(),getImageHeight()*getScaleY());
    }

    public Palette getPalette() {
        return palette;
    }

    @Override
    public UnitPart getFeature() {
        return unitPart;
    }

    @Override
    public Drawable getDrawable() {
        int index = unitPart.property.ordinal();
        if (this.unitPart.bodyType.equals(BodyType.base)) return null;
        if (index > spriteWidth * spriteHeight) return null;
        return new TextureRegionDrawable(regions[index / spriteWidth][index % spriteWidth]);
    }

    public boolean shouldFlip(){
        return false;
    }

    public Drawable getPrefDrawable(){
        int index = unitPart.property.ordinal();
        if (this.unitPart.bodyType.equals(BodyType.base)) return null;
        if (index > spriteWidth * spriteHeight) return null;
        return new TextureRegionDrawable(regions[index / spriteWidth][index % spriteWidth]);
    }
}
