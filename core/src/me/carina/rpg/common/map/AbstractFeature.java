package me.carina.rpg.common.map;

//Basic design around actual feature objects
//1. The parent object, which holds all the info processed by sever
//2. The Actor object, which holds rendering info, and renders itself
//3. The definition object, which is provided by assets in the form of json, which is used to construct parent
public abstract class AbstractFeature {
    public abstract <T extends AbstractFeature> AbstractFeatureProvider<T> toActor();
}
