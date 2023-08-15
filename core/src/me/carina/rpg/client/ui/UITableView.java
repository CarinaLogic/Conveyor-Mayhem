package me.carina.rpg.client.ui;

import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import me.carina.rpg.Game;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.file.Path;

public class UITableView extends Table implements UIElement{
    public UITableView() {
        setBackground(Game.getClient().getAssets().get(new Path("core", AssetGroup.ui, "frame"), NinePatchDrawable.class));
    }
    public UITableView pos(float x, float y){
        setPosition(x,y);
        return this;
    }

    @Override
    public UITableView align(int align) {
        setPosition(getX(),getY(),align);
        return this;
    }

    @Override
    public UITableView size(float width, float height) {
        setSize(width, height);
        return this;
    }
}
