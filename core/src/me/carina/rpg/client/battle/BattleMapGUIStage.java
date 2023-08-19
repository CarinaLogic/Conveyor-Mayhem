package me.carina.rpg.client.battle;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import me.carina.rpg.Game;
import me.carina.rpg.client.misc.CursorListener;
import me.carina.rpg.client.scenes.GUIStage;
import me.carina.rpg.client.ui.UILabel;
import me.carina.rpg.client.ui.UILabelButton;
import me.carina.rpg.client.ui.UITableView;
import me.carina.rpg.client.ui.UIVerticalListView;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.file.Path;
import me.carina.rpg.packets.C2STestPacket;
import me.carina.rpg.packets.connection.Connection;

public class BattleMapGUIStage extends GUIStage {

    @Override
    public void init(Table table) {
        UITableView testView = new UITableView();
        table.add(testView).expand().top().right();
        testView.add(new UILabel().text("Testing"));
        testView.row();
        testView.add(new UILabel().text("This is row 2 with 3 items"));
        testView.add(new UILabel().text("hi!"));
        testView.add(new UILabel().text("bruh moment"));
        testView.row();
        testView.add(new UILabel().text("Row 3 has 2 items"));
        testView.add(new UILabel().text("Hai"));
        table.row();
        UIVerticalListView listView = new UIVerticalListView();
        table.add(listView).bottom().right().maxHeight(150);
        UILabelButton button = new UILabelButton().text("LMAO");
        listView.add(button);
        listView.add(new UILabelButton().text("光の剣（攻撃力128）"));
        listView.add(new UILabelButton().text("This is a very very very long text"));
        button.addListener(new CursorListener());
        setKeyboardFocus(button);
        setDebugAll(true);
    }
}
