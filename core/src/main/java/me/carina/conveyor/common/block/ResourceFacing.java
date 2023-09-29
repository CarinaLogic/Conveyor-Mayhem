package me.carina.conveyor.common.block;

import com.badlogic.gdx.math.Vector3;

public class ResourceFacing {
    ResourceFlow flow;
    Vector3 direction;
    boolean clogged;
    public ResourceFacing(ResourceFlow flow){
        this.flow = flow;
        this.clogged = true;
    }

    public void setClogged(boolean clogged) {
        this.clogged = clogged;
    }

    public ResourceFlow getFlow() {
        return flow;
    }
    public void merge(ResourceFlow flow){
        this.flow = this.flow.merge(flow);
    }
}
