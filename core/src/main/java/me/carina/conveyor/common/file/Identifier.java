package me.carina.conveyor.common.file;

import java.util.Objects;

public class Identifier {
    String nameSpace;
    String id;
    public Identifier(){} //for json
    public Identifier(String nameSpace, String id){
        this.nameSpace = nameSpace;
        this.id = id;
    }

    public String getIdString() {
        return id;
    }

    public String getNameSpace() {
        return nameSpace;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Identifier that = (Identifier) o;
        return Objects.equals(nameSpace, that.nameSpace) && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameSpace, id);
    }

    public Path toPath(AssetGroup group){
        return new Path(nameSpace,group,id);
    }
}