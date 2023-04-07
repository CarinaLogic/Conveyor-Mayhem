package me.carina.rpg.common;

public interface GameObject {
    AbstractGameInstance getGame();
    GameObject setGame(AbstractGameInstance game);
}
