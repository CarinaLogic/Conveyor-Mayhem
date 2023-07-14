package me.carina.rpg.client.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import me.carina.rpg.Game;
import me.carina.rpg.common.AbstractGameInstance;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.file.Path;

public class UILabel extends Label {
    public UILabel(){
        super("",new LabelStyle(Game.getInstance().getAssets().get(new Path("core", AssetGroup.ui, "font"), BitmapFont.class), Color.WHITE));
        getBitmapFontCache().setUseIntegerPositions(false);
    }
    public UILabel text(String text){
        setText(text);
        return this;
    }
    public UILabel text(int value){
        setText(value);
        return this;
    }

    public UILabel fontHeight(float height){
        setFontScale(height / getBitmapFontCache().getFont().getLineHeight());
        return this;
    }
    public UILabel center(){
        setAlignment(Align.center,Align.center);
        return this;
    }
    public UILabel pos(float x, float y){
        setPosition(x, y);
        return this;
    }
    public UILabel color(Color color){
        setColor(color);
        return this;
    }
}
