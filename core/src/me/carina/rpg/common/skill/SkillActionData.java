package me.carina.rpg.common.skill;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import me.carina.rpg.common.unit.Unit;

import java.util.Arrays;

public class SkillActionData {
    String rawData;
    public SkillActionData(String rawData){
        this.rawData = rawData;
    }
    public Action parse(){
        SequenceAction seq = new SequenceAction();
        for (String s : rawData.split("\\n")) {
            seq.addAction(parseLine(s));
        }
        return seq;
    }
    public Action parseLine(String data){
        String[] args = data.split(" ");
        return SkillActionCommand.valueOf(args[0]).eval(Arrays.copyOfRange(args,1,args.length));
    }
}
