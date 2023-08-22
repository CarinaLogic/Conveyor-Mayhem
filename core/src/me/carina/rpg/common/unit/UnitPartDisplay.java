package me.carina.rpg.common.unit;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import me.carina.rpg.Game;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.FlatImageDisplay;
import me.carina.rpg.common.util.Palette;

public class UnitPartDisplay extends FlatImageDisplay implements Display<UnitPart> {
    UnitPart unitPart;
    TextureRegion[][] regions;
    Palette palette;
    int spriteWidth = 1;
    int spriteHeight = 1;
    public UnitPartDisplay(UnitPart unitPart){
        this.unitPart = unitPart;
    }

    public void draw(Batch batch, float parentAlpha) {
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
            Palette basePalette = part.getDisplay(UnitPartDisplay.class).getPalette();
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
        setFlipX(shouldFlip());
        super.draw(batch, parentAlpha);
    }

    public Palette getPalette() {
        return palette;
    }

    @Override
    public float getDisplayX() {
        return Game.getInstance().getContext().get(Unit.class).x;
    }

    @Override
    public float getDisplayY() {
        return Game.getInstance().getContext().get(Unit.class).y;
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
    public int getAlignment() {
        return Align.bottom;
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
        Vector3 camDir = getStage().getCamera().direction.cpy();
        float camRot = (float) Math.atan2(camDir.y,camDir.x);
        float delta = camRot - Game.getInstance().getContext().get(Unit.class).getDisplay(UnitDisplay.class).getFacing();
        return (delta+2*Math.PI) % (2*Math.PI) < Math.PI;
    }

}
