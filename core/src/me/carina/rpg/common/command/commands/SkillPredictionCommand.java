package me.carina.rpg.common.command.commands;

import me.carina.rpg.common.command.Command;
import me.carina.rpg.common.stat.Affinity;
import me.carina.rpg.common.stat.StatType;

public class SkillPredictionCommand extends Command {
    public void skill_hit(Double hitRate){
        getParser().setData("skill_hit",hitRate);
    }
    public void skill_variance(Double variance){
        getParser().setData("skill_variance",variance);
    }
    public void skill_power(Double power){
        getParser().setData("skill_power",power);
    }
    public void skill_affinity(Affinity affinity){
        getParser().setData("skill_affinity",affinity);
    }
    public void skill_stat(StatType statType){
        getParser().setData("skill_stat",statType);
    }
    public void skill_fire_attack(){

    }
    public void skill_fire_effect(){

    }
}
