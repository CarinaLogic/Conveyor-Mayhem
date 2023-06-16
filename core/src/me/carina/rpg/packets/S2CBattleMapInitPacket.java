package me.carina.rpg.packets;

import me.carina.rpg.client.Client;
import me.carina.rpg.client.scenes.BattleScreen;
import me.carina.rpg.common.battle.BattleMap;
import me.carina.rpg.packets.connection.C2SConnection;

public class S2CBattleMapInitPacket extends S2CPacket{
    BattleMap map;
    public S2CBattleMapInitPacket(){} //for json
    public S2CBattleMapInitPacket(BattleMap map){
        this.map = map;
    }
    @Override
    public void onRecieve(Client client, C2SConnection connection) {
        BattleScreen battleScreen = new BattleScreen();
        client.queueScreen(battleScreen);
        battleScreen.setBattleMap(map);
    }
}
