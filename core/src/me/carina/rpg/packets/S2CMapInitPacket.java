package me.carina.rpg.packets;

import me.carina.rpg.client.Client;
import me.carina.rpg.client.scenes.BattleScreen;
import me.carina.rpg.common.world.map.WorldMap;
import me.carina.rpg.packets.connection.C2SConnection;

public class S2CMapInitPacket extends S2CPacket{
    WorldMap map;
    public S2CMapInitPacket(WorldMap map){
        this.map = map;
    }
    @Override
    public void onRecieve(Client client, C2SConnection connection) {
        BattleScreen battleScreen = new BattleScreen(client);
        client.setScreen(battleScreen);
        battleScreen.setBattleMap((WorldMap) map.setGame(client));
    }
}
