package me.carina.rpg.common.world;

public class BlockDef extends WorldComponentDef {
    public static BlockDef fromId(String id, AbstractGameInstance game) {
        return WorldComponentDef.fromId("block/" + id, BlockDef.class, game);
    }

}
