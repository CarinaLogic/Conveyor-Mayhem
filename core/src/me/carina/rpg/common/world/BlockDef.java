package me.carina.rpg.common.world;

public class BlockDef extends WorldComponentDef {
    public static BlockDef fromId(String id, AbstractGameInstance game) {
        return (BlockDef) WorldComponentDef.fromId("block/" + id, game);
    }

}
