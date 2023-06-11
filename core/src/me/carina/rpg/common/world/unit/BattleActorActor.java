package me.carina.rpg.common.world.unit;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import me.carina.rpg.client.scenes.BattleMapStage;
import me.carina.rpg.client.ui.UILabel;

public abstract class BattleActorActor extends Group {
    Drawable icon;
    BattleActor battleActor;
    UILabel topLabel;
    UILabel bottomLabel;
    UILabel leftLabel;
    UILabel rightLabel;
    public BattleActorActor(BattleActor battleActor){
        this.battleActor = battleActor;
        icon = battleActor.getGame().getAssets().get(battleActor.getPath(), Drawable.class);
        setSize(1,1);
        topLabel = new UILabel(battleActor.getGame()).pos(0.5f,0.85f).fontHeight(0.3f).center();
        bottomLabel = new UILabel(battleActor.getGame()).pos(0.5f,0.15f).fontHeight(0.3f).center();
        leftLabel = new UILabel(battleActor.getGame()).pos(0.85f,0.5f).fontHeight(0.3f).center();
        rightLabel = new UILabel(battleActor.getGame()).pos(0.15f,0.5f).fontHeight(0.3f).center();
        addActor(topLabel);
        addActor(bottomLabel);
        addActor(leftLabel);
        addActor(rightLabel);
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
        topLabel.text(battleActor.top.attack.value);
        bottomLabel.text(battleActor.bottom.attack.value);
        leftLabel.text(battleActor.left.attack.value);
        rightLabel.text(battleActor.right.attack.value);
    }
}
