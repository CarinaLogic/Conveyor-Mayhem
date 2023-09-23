package me.carina.conveyor.common.command;

public class DataRange {
    //INCLUSIVE ON BOTH SIDES
    float min = 0;
    boolean minSpecified = false;
    float max = 0;
    boolean maxSpecified = false;
    public static DataRange min(float min){
        DataRange range = new DataRange();
        range.setMin(min);
        return range;
    }
    public static DataRange max(float max){
        DataRange range = new DataRange();
        range.setMax(max);
        return range;
    }
    public static DataRange minMax(float min, float max){
        DataRange range = new DataRange();
        range.setMin(min);
        range.setMax(max);
        return range;
    }

    public boolean isInRange(float value){
        if (minSpecified && value < min) return false;
        if (maxSpecified && value > max) return false;
        return true;
    }

    public void setMax(float max) {
        this.max = max;
        this.maxSpecified = true;
    }

    public void setMin(float min) {
        this.min = min;
        this.minSpecified = true;
    }
}
