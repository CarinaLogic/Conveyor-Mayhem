package me.carina.rpg.common.unit;

import me.carina.rpg.common.*;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.file.Identifier;
import me.carina.rpg.common.file.Path;

//Display properties have 4 layers
//1. BodyType, represents which type of body to draw (hair, eyes, etc)
//2. Identifier, represents which asset (in body type directory) to load (hair1, hair2, etc)
//3. BodyProperty, represents which texture region to use in the asset (open/closed eye, arm rotation, etc)
//4. colorIndex, represents which color palette to use
//Textures are array multiple 32x32 images, with a color palette attached to the right
//Images are indexed from top left to right, then go down one & start over from left
//Color palette starts from top left of leftover space, going down
//Colors are first replaced with base color (defines skin color), don't use same color as skin for clothes
//if bodyType, id, or colorIndex was changed, new instance should be crated, BodyProperty works fine
public class UnitPart extends Feature {
    BodyType bodyType;
    //Identifier id; //already defined in Feature
    BodyProperty property;
    int colorIndex;
    public UnitPart(){} //for json
    public UnitPart(BodyType bodyType, Identifier id, BodyProperty property, int colorIndex){
        this.bodyType = bodyType;
        setId(id);
        this.property = property;
        this.colorIndex = colorIndex;
    }
    public Path getPath(){
        return new Path(getId().getNameSpace(), getAssetGroup(), bodyType+"_"+getId().getIdString());
    }

    @Override
    public void tick() {

    }

    @Override
    public AssetGroup getAssetGroup() {
        return AssetGroup.units;
    }

}
