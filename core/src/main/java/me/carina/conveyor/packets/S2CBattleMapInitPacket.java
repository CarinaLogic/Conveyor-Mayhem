package me.carina.conveyor.packets;

import me.carina.conveyor.client.Client;
import me.carina.conveyor.client.battle.BattleScreen;
import me.carina.conveyor.common.battle.BattleMap;
import me.carina.conveyor.packets.connection.C2SConnection;

public class S2CBattleMapInitPacket extends S2CPacket{
    BattleMap map;
    public S2CBattleMapInitPacket(){} //for json
    public S2CBattleMapInitPacket(BattleMap map){
        this.map = map;
    }
    @Override
    public void onRecieve(Client client, C2SConnection connection) {
        BattleScreen battleScreen = new BattleScreen(map);
        client.queueScreen(battleScreen);
    }
}
