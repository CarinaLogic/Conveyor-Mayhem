package me.carina.rpg.common.command;

public class RangeIntNode extends IntNode{
    int min = Integer.MIN_VALUE;
    int max = Integer.MAX_VALUE;

    @Override
    public boolean isApplicable(Integer obj) {
        return min <= obj && obj < max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setRange(int min, int max){
        this.min = min;
        this.max = max;
    }
}
