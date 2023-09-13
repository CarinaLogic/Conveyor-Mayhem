package me.carina.conveyor.common.file;

import java.util.Objects;

public class Path {
    String nameSpace;
    AssetGroup group;
    String id;
    public Path(String nameSpace, AssetGroup group, String id){
        this.nameSpace = nameSpace;
        this.group = group;
        this.id = id;
    }

    public static Path reg(AssetGroup group, String id){
        return new Path("reg",group,id);
    }

    public Identifier toIdentifier(){
        return new Identifier(nameSpace,id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Path path = (Path) o;
        return Objects.equals(nameSpace, path.nameSpace) && group == path.group && Objects.equals(id, path.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameSpace, group, id);
    }

    @Override
    public String toString() {
        return nameSpace + ":" + group + "/" + id;
    }
    public static Path fromString(String s){
        String nameSpace;
        AssetGroup group;
        String id;
        String[] s1 = s.split(":");
        String s2;
        if (s1.length == 1) {
            nameSpace = "core"; //if no namespace provided, automatically assumes it's core
            s2 = s;
        }
        else {
            nameSpace = s1[0];
            s2 = s1[1];
        }
        String[] s3 = s2.split("/");
        if (s3.length == 1) {
            group = AssetGroup.ui; //temp solution
            id = s3[0];
        }
        else{
            group = AssetGroup.valueOf(s3[0]);
            id = s3[1];
        }
        return new Path(nameSpace,group,id);
    }
}
