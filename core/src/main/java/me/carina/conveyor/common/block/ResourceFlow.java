package me.carina.conveyor.common.block;

import com.badlogic.gdx.math.Vector3;
import me.carina.conveyor.common.ChildFeature;
import me.carina.conveyor.common.Definition;
import me.carina.conveyor.common.Feature;
import me.carina.conveyor.common.command.DataRange;
import me.carina.conveyor.common.file.AssetGroup;
import me.carina.conveyor.common.resource.Resource;

public class ResourceFlow extends Feature {
    @ChildFeature
    Resource resource;
    float flow;
    DataRange size;
    DataRange heat;


    @Override
    public AssetGroup getAssetGroup() {
        return null;
    }

    @Override
    public void tick() {

    }

    public ResourceFlow merge(ResourceFlow f){
        ResourceFlow nFlow = new ResourceFlow();
        nFlow.flow = this.flow + f.flow;
        nFlow.size = this.size.expand(f.size);
        nFlow.heat = this.heat.expand(f.heat);
        nFlow.resource = this.resource;
        return nFlow;
    }

}
