package me.carina.rpg.common.file;

public class Path {
    String nameSpace;
    AssetGroup group;
    String id;
    public Path(String nameSpace, AssetGroup group, String id){
        this.nameSpace = nameSpace;
        this.group = group;
        this.id = id;
    }

    public Identifier toIdentifier(){
        return new Identifier(nameSpace,id);
    }

    @Override
    public String toString() {
        return nameSpace + ":" + group + "/" + id;
    }
}
