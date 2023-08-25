package me.carina.rpg.client.battle;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import me.carina.rpg.client.misc.CursorListener;
import me.carina.rpg.client.ui.Selectable;
import me.carina.rpg.client.ui.UILabelButton;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.skill.Skill;

@Selectable
public class UIBattleSkillEntry extends UILabelButton implements Display<Skill> {
    Skill skill;
    public UIBattleSkillEntry(Skill skill) {
        super();
        this.skill = skill;
        text(skill.getName());
        setCursorListener(new CursorListener(){
            @Override
            public void enter(InputEvent event) {
                UIBattleSkillEntry.this.enter();
            }

            @Override
            public void exit(InputEvent event) {
                UIBattleSkillEntry.this.exit();
            }

            @Override
            public void keyLeft(InputEvent event) {
                ((UIBattleSkillPanels) getParent().getParent()).goLeft();
            }

            @Override
            public void keyRight(InputEvent event) {
                ((UIBattleSkillPanels) getParent().getParent()).goRight();
            }
        });
    }

    @Override
    public Skill getFeature() {
        return skill;
    }
}
