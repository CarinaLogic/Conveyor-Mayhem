package me.carina.rpg.common.file;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

public class Assets {
    Array<AssetGroup> assetGroups = new Array<>();
    AssetFilter assetFilter;
    TextureAtlas atlas;
    public Assets(AssetFilter filter){
        assetFilter = filter;
    }

}
