package me.carina.rpg.common.map;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import me.carina.rpg.client.scenes.BattleMapStage;

//TODO i want it to be rendered on GUI batch :((
public abstract class AbstractBattleActorActor extends Actor {
    Drawable icon;
    AbstractBattleActor battleActor;
    public AbstractBattleActorActor(AbstractBattleActor battleActor){
        this.battleActor = battleActor;
        icon = battleActor.getGame().getAssets().get(battleActor.id, Drawable.class);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (getStage() instanceof BattleMapStage) {
            Group parent = getParent();
            Vector2 v = new Vector2(parent.getWidth()*0.125f,parent.getHeight()*0.125f);
            parent.localToScreenCoordinates(v);
            Vector2 v1 = new Vector2(parent.getWidth()*0.875f,parent.getHeight()*0.125f);
            parent.localToScreenCoordinates(v1);
            BattleMapStage stage = (BattleMapStage) getStage();
            if (stage.getCanvas() != null) stage.getCanvas().addPicture(icon, v.x, v.y, v1.x- v.x, v1.x-v.x);
        }
    }
    public void drawAtk(Batch batch){

    }
}
