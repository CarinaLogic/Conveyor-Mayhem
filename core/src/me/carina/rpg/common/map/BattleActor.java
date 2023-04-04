package me.carina.rpg.common.map;

import com.badlogic.gdx.utils.Array;
import me.carina.rpg.common.AbstractGameInstance;
import me.carina.rpg.common.file.Identifier;

//named battle actor to distinguish from scene2d Actor
public abstract class BattleActor extends Feature {
    DirectionalParam top;
    DirectionalParam bottom;
    DirectionalParam left;
    DirectionalParam right;
    FractionalParam hp;
    int wt;
    int retreatWtCost;
    int x;
    int y;
    Array<AbstractAction> actions = new Array<>();


    @Override
    public abstract BattleActorActor newActor();

    public static class DirectionalParam{
        ElementalParam attack;
        ElementalParam defence;
        public DirectionalParam(ElementalParam attack, ElementalParam defence){
            this.attack = attack;
            this.defence = defence;
        }
        public DirectionalParam(){}
    }
    public static class ElementalParam{
        int value;
        Element[] elements;
        public ElementalParam(int value, Element... elements){
            this.value = value;
            this.elements = elements;
        }
        public ElementalParam(){}
    }
    public static class FractionalParam{
        int current;
        int max;
        public FractionalParam(int value){
            this.current = value;
            this.max = value;
        }
        public FractionalParam(int current, int max){
            this.current = current;
            this.max = max;
        }
        public FractionalParam(){}
    }
}
