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
import me.carina.rpg.common.unit.Unit;
import me.carina.rpg.packets.C2STestPacket;
import me.carina.rpg.packets.connection.Connection;

public class BattleMapGUIStage extends GUIStage<BattleScreen> {
    int selectedIndex = -1;
    @Override
    public void init(Table table) {
        table.add().height(64).expandX();
        table.row();
        table.add(getScreen().map.units.newDisplay(UIBattleSkillPanels.class)).center().right().expand().padRight(32);
        table.row();
        table.add(getScreen().map.units.newDisplay(UIBattleStatPanels.class)).left();
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }

    public Unit getSelectedUnit(){
        if (selectedIndex == -1) return null;
        return getScreen().map.units.get(selectedIndex);
    }

    @Override
    public boolean goLeft() {
        if (selectedIndex == -1){
            selectedIndex = getScreen().map.units.size()-1;
            return true;
        }
        selectedIndex--;
        if (selectedIndex < 0) selectedIndex = -1;
        return true;
    }

    @Override
    public boolean goRight() {
        if (selectedIndex == -1){
            selectedIndex = 0;
            return true;
        }
        selectedIndex++;
        if (selectedIndex >= getScreen().map.units.size()) selectedIndex = -1;
        return true;
    }
}
