package me.carina.rpg.common.file;

public class Identifier {
    String nameSpace;
    String id;
    public Identifier(String nameSpace, String id){
        this.nameSpace = nameSpace;
        this.id = id;
    }
    public Path toPath(AssetGroup group){
        return new Path(nameSpace,group,id);
    }
}
