package me.carina.rpg.common.block;

import com.badlogic.gdx.math.Vector3;

public class ResourceFacing {
    ResourceFlow flow;
    Vector3 direction;
    boolean clogged;
    public ResourceFacing(ResourceFlow flow){
        this.flow = flow;
        this.clogged = false;
    }

    public void setClogged(boolean clogged) {
        this.clogged = clogged;
    }

    public ResourceFlow getFlow() {
        return flow;
    }
    public void merge(ResourceFlow flow){
        if (this.flow.resource.equals(flow.resource)){
            this.flow.flow += flow.flow;
        }
        else {
            this.clogged = true;
        }
    }

    public boolean isClogged() {
        return clogged;
    }
}
