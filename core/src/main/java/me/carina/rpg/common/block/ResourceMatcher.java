package me.carina.rpg.common.block;

import me.carina.rpg.Game;
import me.carina.rpg.common.command.DataRange;
import me.carina.rpg.common.file.Identifier;
import me.carina.rpg.common.resource.Resource;
import me.carina.rpg.common.resource.ResourceType;

public class ResourceMatcher {
    ResourceType type;
    Identifier id;
    DataRange flow;
    DataRange size;
    DataRange heat;
    float consumption;
    public boolean matches(ResourceFlow resourceFlow){
        if (type != null && !type.equals(resourceFlow.resource.getType())) return false;
        if (id != null && !id.equals(resourceFlow.resource.getId())) return false;
        if (flow != null && !flow.isInRange(resourceFlow.flow)) return false;
        if (size != null && !size.isInRange(resourceFlow.size)) return false;
        if (heat != null && !heat.isInRange(resourceFlow.heat)) return false;
        return true;
    }

    public void setId(Identifier id) {
        this.id = id;
    }

    public void setFlow(DataRange flow) {
        this.flow = flow;
    }

    public void setHeat(DataRange heat) {
        this.heat = heat;
    }

    public void setSize(DataRange size) {
        this.size = size;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

    public void setConsumption(float consumption) {
        this.consumption = consumption;
    }
    public float getConsumption() {
        return consumption;
    }

    public ResourceFlow constructFlow(){
        ResourceFlow flow = new ResourceFlow();
        flow.resource = Game.getInstance().getAssets().get(id,Resource.class);
        flow.heat = heat;
        flow.size = size;
        flow.flow = this.flow.getMax();
        return flow;
    }
}
