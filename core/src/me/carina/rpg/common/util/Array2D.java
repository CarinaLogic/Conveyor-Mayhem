package me.carina.rpg.common.util;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.reflect.ArrayReflection;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.Vector;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 2D array implementation using 1D array because gwt doesn't support 2D array serialization
 * @param <T> type of values
 */
public class Array2D<T> implements Iterable<Array2D.Array2DEntry<T>>{
    T[] values;
    int width;
    int height;
    public Array2D(){
        //Make a 1x1 array so that values doesn't stay null thus preventing exception upon values.length call
        this(1,1);
    } //for json
    public Array2D(int width, int height){
        //this.values = (T[]) new Object[width*height];
        //above does not work on gwt
        Array<T> tempArray = new Array<>();
        tempArray.setSize(width*height);
        this.values = tempArray.shrink();
        this.width = width;
        this.height = height;
    }
    public T get(int x, int y){
        return values[x+y*width];
    }
    public T get(Vector2 v){
        return get((int) Math.floor(v.x), (int) Math.floor(v.y));
    }
    public void set(int x, int y, T obj){
        values[x+y*width] = obj;
    }
    public void set(Vector2 v, T obj){
        set((int) Math.floor(v.x), (int) Math.floor(v.y),obj);
    }
    public void fill(T obj){
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < values.length / width; y++) {
                set(x,y,obj);
            }
        }
    }
    public void fill(Function<Vector2,T> func){
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < values.length / width; y++) {
                set(x,y,func.apply(new Vector2(x,y)));
            }
        }
    }
    public void clear(){
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < values.length / width; y++) {
                set(x,y,null);
            }
        }
    }
    public Array2D<T> resize(int width, int height){
        return resize(0,height - this.values.length / this.width,0, width - this.width);
    }
    public Array2D<T> resize(int top, int bottom, int left, int right){
        Array2D<T> oldArray = this.copy();
        this.width = oldArray.width+top+bottom;
        this.height = oldArray.values.length/oldArray.width+left+right;
        Array<T> tempArray = new Array<>();
        tempArray.setSize(width*height);
        this.values = tempArray.shrink();
        for (int x = 0; x < oldArray.width; x++) {
            for (int y = 0; y < oldArray.values.length / oldArray.width; y++) {
                int tx = x + left;
                int ty = y + top;
                if (0 <= tx && tx < oldArray.width + right && 0 <= ty && ty < oldArray.values.length/oldArray.width+bottom){
                    this.set(tx,ty,get(x,y));
                }
            }
        }
        return this;
    }
    public int getX(T obj){
        if (obj == null) return -1;
        for (Array2DEntry<T> entry : this) {
            if (obj.equals(entry.value)) return entry.x;
        }
        return -1;
    }
    public int getY(T obj){
        if (obj == null) return -1;
        for (Array2DEntry<T> entry : this) {
            if (obj.equals(entry.value)) return entry.y;
        }
        return -1;
    }
    public Vector2 getPos(T obj){
        if (obj == null) return null;
        for (Array2DEntry<T> entry : this) {
            if (obj.equals(entry.value)) return new Vector2(entry.x,entry.y);
        }
        return null;
    }
    public int getIdenticalX(T obj){
        if (obj == null) return -1;
        for (Array2DEntry<T> entry : this) {
            if (obj == entry.value) return entry.x;
        }
        return -1;
    }
    public int getIdenticalY(T obj){
        if (obj == null) return -1;
        for (Array2DEntry<T> entry : this) {
            if (obj == entry.value) return entry.y;
        }
        return -1;
    }
    public Vector2 getIdenticalPos(T obj){
        if (obj == null) return null;
        for (Array2DEntry<T> entry : this) {
            if (obj == entry.value) return new Vector2(entry.x,entry.y);
        }
        return null;
    }

    public int getWidth(){return width;}
    public int getHeight(){return values.length/width;}

    public Array2D<T> copy(){
        Array2D<T> array = new Array2D<>();
        array.values = Arrays.copyOf(values,values.length);
        array.width = width;
        array.height = height;
        return array;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Array2D<?> array2D = (Array2D<?>) o;
        return width == array2D.width && height == array2D.height && Arrays.equals(values, array2D.values);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(width, height);
        result = 31 * result + Arrays.hashCode(values);
        return result;
    }

    @Override
    public Iterator<Array2DEntry<T>> iterator() {
        return new Array2DIterator<>(this);
    }


    public static class Array2DEntry<T>{
        public int x;
        public int y;
        public T value;
        public Array2DEntry(int x, int y, T value){
            this.x = x;
            this.y = y;
            this.value = value;
        }
        public Vector2 asVector(){
            return new Vector2(x,y);
        }
    }
    public static class Array2DIterator<T> implements Iterator<Array2DEntry<T>> {
        Array2D<T> array;
        int index;
        public Array2DIterator(Array2D<T> array){
            this.array = array;
        }

        @Override
        public boolean hasNext() {
            return array.values.length > index;
        }

        @Override
        public Array2DEntry<T> next() {
            Array2DEntry<T> entry = new Array2DEntry<>(index%array.width,index/array.width, array.values[index]);
            index++;
            return entry;
        }

        @Override
        public void remove() {
            array.values[index-1] = null;
        }
    }
}
