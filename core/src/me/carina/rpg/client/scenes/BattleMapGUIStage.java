package me.carina.rpg.client.scenes;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;
import me.carina.rpg.Game;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.file.Path;
import me.carina.rpg.packets.C2STestPacket;
import me.carina.rpg.packets.connection.Connection;

public class BattleMapGUIStage extends GUIStage{

    @Override
    public void init(Table table) {
        Button button = new Button(Game.getInstance().getAssets().get(new Path("core", AssetGroup.ui,"up"), Drawable.class),
                Game.getInstance().getAssets().get(new Path("core",AssetGroup.ui,"up"), Drawable.class));
        table.add(button).top().left().size(3);
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
            for (Connection connection : new Array.ArrayIterator<>(Game.getInstance().getConnections())) {
                connection.send(new C2STestPacket());
            }
            }
        });
        setDebugAll(true);
    }
}
