package me.carina.rpg.common;

import me.carina.rpg.common.file.Identifier;

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
