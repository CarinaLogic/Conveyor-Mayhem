package me.carina.rpg.client.battle;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import me.carina.rpg.Game;
import me.carina.rpg.client.misc.CursorListener;
import me.carina.rpg.client.scenes.BaseScreen;
import me.carina.rpg.client.scenes.GUIStage;
import me.carina.rpg.client.ui.*;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.file.Path;
import me.carina.rpg.packets.C2STestPacket;
import me.carina.rpg.packets.connection.Connection;

public class BattleMapGUIStage extends GUIStage<BattleScreen> {

    @Override
    public void init(Table table) {
        table.add().height(64).expandX();
        table.row();
        table.add(getScreen().map.units.newDisplay(UIBattleSkillPanels.class)).center().right().expand().padRight(64);
        table.row();
        table.add(getScreen().map.units.newDisplay(UIBattleStatPanels.class)).left();
    }
}
