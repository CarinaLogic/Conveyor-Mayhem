package me.carina.conveyor.client.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import me.carina.conveyor.Game;
import me.carina.conveyor.common.file.AssetGroup;
import me.carina.conveyor.common.file.Path;

public class UITableView extends Table{
    public UITableView() {
        pad(6);
        setBackground(Game.getClient().getAssets().get(new Path("core", AssetGroup.ui, "frame"), NinePatchDrawable.class));
    }
}
