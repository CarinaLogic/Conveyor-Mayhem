package me.carina.rpg.common.util;

import com.badlogic.gdx.math.Vector2;

import java.util.Arrays;
import java.util.Iterator;

/**
 * 2D array implementation using 1D array because gwt doesn't support 2D array serialization
 * @param <T> type of values
 */
public class Array2D<T> implements Iterable<Array2D.Array2DEntry<T>>{
    T[] values;
    int width;
    public Array2D(int width, int height){
        //noinspection unchecked
        this.values = (T[]) new Object[width*height];
        this.width = width;
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
        Arrays.fill(values, obj);
    }
    public Array2D<T> resize(int width, int height){
        return resize(0,height - this.values.length / this.width,0, width - this.width);
    }
    public Array2D<T> resize(int top, int bottom, int left, int right){
        Array2D<T> newArray = new Array2D<>(this.width+top+bottom,this.values.length/this.width+left+right);
        newArray.fill(null);
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.values.length / this.width; y++) {
                int tx = x + left;
                int ty = y + top;
                if (0 <= tx && tx < this.width + right && 0 <= ty && ty < this.values.length/this.width+bottom){
                    newArray.set(tx,ty,get(x,y));
                }
            }
        }
        return newArray;
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
