package me.carina.rpg.client.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import me.carina.rpg.Game;
import me.carina.rpg.client.misc.CursorListener;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.file.Path;

@Selectable
public class UILabelButton extends TextButton{
    public UILabelButton(){
        super("", getPrefStyle());
        getLabel().setAlignment(Align.left);
        getLabelCell().left().expandX();
        addListener(new CursorListener());
    }
    public UILabelButton text(String text){
        setText(text);
        return this;
    }

    public UILabelButton color(Color color){
        setColor(color);
        return this;
    }

    public static TextButtonStyle getPrefStyle(){
        TextButtonStyle style = new TextButtonStyle();
        style.over = Game.getClient().getAssets().get(new Path("core",AssetGroup.ui,"hovered"), Drawable.class);
        style.checked = Game.getClient().getAssets().get(new Path("core",AssetGroup.ui,"selected"), Drawable.class);
        style.font = Game.getClient().getAssets().get(new Path("core",AssetGroup.ui,"font"), BitmapFont.class);
        return style;
    }

    @Override
    public boolean isOver() {
        boolean s = super.isOver();
        if (s) return true;
        Stage stage = getStage();
        if (stage == null) return false;
        Actor f = stage.getKeyboardFocus();
        if (f == null) return false;
        return f == this;
    }
}
