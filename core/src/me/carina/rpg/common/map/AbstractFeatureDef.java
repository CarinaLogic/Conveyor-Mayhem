package me.carina.rpg.common.map;

import com.badlogic.gdx.scenes.scene2d.Actor;
import me.carina.rpg.client.Client;
import me.carina.rpg.common.AbstractGameInstance;
import me.carina.rpg.common.file.AssetFilterProvider;
import me.carina.rpg.server.Server;

public abstract class AbstractFeatureDef<T extends AbstractFeature> {
    public abstract T toFeature(AbstractGameInstance game);
    public abstract Actor toActor(Client game);
}
