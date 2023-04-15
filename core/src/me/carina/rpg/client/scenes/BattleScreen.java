package me.carina.rpg.client.scenes;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import me.carina.rpg.client.Client;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.file.Path;
import me.carina.rpg.common.map.BattleMap;

public class BattleScreen extends BaseScreen{
    BattleMapStage battleMapStage;
    BattleMapActorStage actorStage;
    BattleMapGUIStage guiStage;
    public BattleScreen(Client game) {
        super(game);
    }

    @Override
    public void show() {
        battleMapStage = new BattleMapStage();
        addStage(battleMapStage);
        actorStage = new BattleMapActorStage();
        addStage(actorStage);
        guiStage = new BattleMapGUIStage();
        addStage(guiStage);
        battleMapStage.setCanvas(actorStage.canvas);
    }
    public void setBattleMap(BattleMap battleMap){
        battleMapStage.setBattleMap(battleMap);
    }
}
