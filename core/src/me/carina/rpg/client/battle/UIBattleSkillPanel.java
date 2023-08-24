package me.carina.rpg.client.battle;

import com.badlogic.gdx.graphics.g2d.Batch;
import me.carina.rpg.Game;
import me.carina.rpg.client.ui.UITableView;
import me.carina.rpg.client.ui.UIVerticalSelection;
import me.carina.rpg.common.ArrayDisplayHandler;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.Feature;
import me.carina.rpg.common.skill.Skills;

public class UIBattleSkillPanel extends UIVerticalSelection implements Display<Skills> {
    Skills skills;
    ArrayDisplayHandler handler = new ArrayDisplayHandler(
            this, feature -> add(feature.newDisplay(UIBattleSkillEntry.class))
    ) {
        @Override
        public Iterable<? extends Feature> getIterable() {
            return skills;
        }
    };
    @Override
    public void draw(Batch batch, float parentAlpha) {
        Game.getInstance().getContext().add(getFeature());
        handler.tick();
        super.draw(batch, parentAlpha);
    }
    public UIBattleSkillPanel(Skills skills){
        this.skills = skills;
    }
    @Override
    public Skills getFeature() {
        return skills;
    }
}
