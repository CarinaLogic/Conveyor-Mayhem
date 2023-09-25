package me.carina.conveyor.common;

import me.carina.conveyor.common.file.Identifier;

public abstract class Definition<T extends Defined> implements Identifiable{
    Identifier id;
    public abstract Class<T> getDefinedClass();
    public abstract void init(T definedObject);

    @Override
    public void setId(Identifier id) {
        this.id = id;
    }

    @Override
    public Identifier getId() {
        return id;
    }
}
