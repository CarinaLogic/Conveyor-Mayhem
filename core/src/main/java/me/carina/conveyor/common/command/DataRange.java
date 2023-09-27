package me.carina.conveyor.common.command;

import com.badlogic.gdx.math.MathUtils;

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

    public DataRange(){}//for json
    public DataRange(float min, float max, boolean minSpecified, boolean maxSpecified){
        this.min = min;
        this.max = max;
        this.minSpecified = minSpecified;
        this.maxSpecified = maxSpecified;
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

    public DataRange expand(DataRange range){
        float mi = 0, ma = 0;
        boolean mis, mas;
        mis = range.minSpecified && this.minSpecified;
        if (mis) {
            mi = Math.min(this.min,range.min);
        }
        mas = range.maxSpecified && this.maxSpecified;
        if (mas){
            ma = Math.max(this.max, range.max);
        }
        return new DataRange(mi,ma,mis,mas);
    }
    public DataRange shrink(DataRange range){
        float mi = 0, ma = 0;
        boolean mis, mas;
        mis = range.minSpecified || this.minSpecified;
        if (mis) {
            if (!range.minSpecified) mi = this.min;
            else if (!this.minSpecified) mi = range.min;
            else mi = Math.max(this.min,range.min);
        }
        mas = range.maxSpecified || this.maxSpecified;
        if (mas){
            if (!range.maxSpecified) ma = this.max;
            else if (!this.maxSpecified) ma = range.max;
            else ma = Math.min(this.max, range.max);
        }
        return new DataRange(mi,ma,mis,mas);
    }
}
