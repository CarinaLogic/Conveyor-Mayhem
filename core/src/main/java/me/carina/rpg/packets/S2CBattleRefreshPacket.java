package me.carina.rpg.packets;

import me.carina.rpg.Game;
import me.carina.rpg.client.Client;
import me.carina.rpg.client.battle.BattleScreen;
import me.carina.rpg.common.battle.BattleMap;
import me.carina.rpg.common.faction.Factions;
import me.carina.rpg.packets.connection.C2SConnection;

public class S2CBattleRefreshPacket extends S2CPacket{
    BattleMap battleMap;
    public S2CBattleRefreshPacket(){
        this.battleMap = Game.getInstance().getContext().get(BattleMap.class);
    }

    @Override
    public void onRecieve(Client client, C2SConnection connection) {
        BattleScreen screen = client.getScreen(BattleScreen.class);
        if (screen != null){
            screen.setBattleMap(battleMap);
        }
    }
}
