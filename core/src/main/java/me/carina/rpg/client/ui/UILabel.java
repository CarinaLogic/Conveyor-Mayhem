package me.carina.rpg.client.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import me.carina.rpg.Game;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.file.Path;

import java.util.function.Supplier;

public class UILabel extends Label{
    boolean shadowed = true;
    Supplier<String> stringSupplier = ()->"";
    Supplier<Color> colorSupplier = ()->Color.WHITE;
    public UILabel(){
        super("",new LabelStyle(
                Game.getInstance().getAssets().get(new Path("core", AssetGroup.ui, "font"), BitmapFont.class),
                Color.WHITE));
        getBitmapFontCache().setUseIntegerPositions(false);
    }
    public UILabel supplyString(Supplier<String> supplier){
        this.stringSupplier = supplier;
        return this;
    }

    public UILabel supplyColor(Supplier<Color> supplier){
        colorSupplier = supplier;
        return this;
    }

    @Override
    public void act(float delta) {
        if (stringSupplier != null) setText(stringSupplier.get());
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        validate();
        Color color = getColor().cpy();
        color.a *= parentAlpha;
        if (getStyle().background != null) {
            batch.setColor(color.r, color.g, color.b, color.a);
            getStyle().background.draw(batch, getX(), getY(), getWidth(), getHeight());
        }
        if (getStyle().fontColor != null) color.mul(getStyle().fontColor);
        if (shadowed){
            getBitmapFontCache().tint(color.cpy().lerp(Color.BLACK,0.7f).clamp());
            getBitmapFontCache().setPosition(getX()+1, getY());
            getBitmapFontCache().draw(batch);
            getBitmapFontCache().setPosition(getX(), getY()-1);
            getBitmapFontCache().draw(batch);
        }
        getBitmapFontCache().tint(color);
        getBitmapFontCache().setPosition(getX(), getY());
        getBitmapFontCache().draw(batch);
    }

    public UILabel color(Color color){
        setColor(color);
        return this;
    }

    public UILabel plain(){
        shadowed = false;
        return this;
    }

}
