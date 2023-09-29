package me.carina.conveyor.common.command;

import com.badlogic.gdx.math.MathUtils;

import java.util.Objects;

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
    public boolean isInRange(DataRange parent){
        return parent.expand(this).equals(parent);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataRange range = (DataRange) o;
        return Float.compare(min, range.min) == 0 && minSpecified == range.minSpecified && Float.compare(max, range.max) == 0 && maxSpecified == range.maxSpecified;
    }

    @Override
    public int hashCode() {
        return Objects.hash(min, minSpecified, max, maxSpecified);
    }
}
