package me.carina.conveyor.common;

import me.carina.conveyor.common.file.Identifier;

public interface Identifiable {
    void setId(Identifier id);
    Identifier getId();
}
