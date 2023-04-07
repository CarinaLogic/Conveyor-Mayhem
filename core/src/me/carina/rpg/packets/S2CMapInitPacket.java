package me.carina.rpg.packets;

import me.carina.rpg.client.Client;
import me.carina.rpg.client.scenes.BattleScreen;
import me.carina.rpg.common.map.BattleMap;

public class S2CMapInitPacket extends S2CPacket{
    BattleMap map;
    public S2CMapInitPacket(BattleMap map){
        this.map = map;
    }
    @Override
    public void onRecieve(Client client) {
        BattleScreen battleScreen = new BattleScreen(client);
        client.setScreen(battleScreen);
        battleScreen.setBattleMap((BattleMap) map.setGame(client));
    }
}
