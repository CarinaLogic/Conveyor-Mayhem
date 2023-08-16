package me.carina.rpg.client.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import me.carina.rpg.Game;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.file.Path;

public class UILabelButton extends TextButton {
    public UILabelButton(){
        super("", new TextButtonStyle(
                null,
                Game.getClient().getAssets().get(new Path("core",AssetGroup.ui,"hovered"), Drawable.class),
                Game.getClient().getAssets().get(new Path("core",AssetGroup.ui,"selected"), Drawable.class),
                Game.getClient().getAssets().get(new Path("core",AssetGroup.ui,"font"), BitmapFont.class)));
    }
    public UILabelButton text(String text){
        setText(text);
        return this;
    }

    public UILabelButton color(Color color){
        setColor(color);
        return this;
    }

}
