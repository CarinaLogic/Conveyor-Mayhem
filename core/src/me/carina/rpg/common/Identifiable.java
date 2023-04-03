package me.carina.rpg.common;

import me.carina.rpg.common.file.Identifier;

public interface Identifiable {
    void setId(Identifier id);
    Identifier getId();
}
