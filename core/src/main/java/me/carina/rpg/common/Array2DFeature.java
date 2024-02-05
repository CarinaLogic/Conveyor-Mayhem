package me.carina.rpg.common;

import com.badlogic.gdx.math.Vector2;
import me.carina.rpg.common.util.Array2D;

import java.util.Iterator;
import java.util.function.Function;

public abstract class Array2DFeature<T extends Feature> extends Feature implements Iterable<Array2D.Array2DEntry<T>>{
    Array2D<T> array = new Array2D<>();
    public T get(int x, int y){
        return array.get(x, y);
    }
    public T get(Vector2 v){
        return array.get(v);
    }
    public void set(int x, int y, T obj){
        array.set(x,y,obj);
    }
    public void set(Vector2 v, T obj){
        array.set(v,obj);
    }
    public void fill(T obj){
        array.fill(obj);
    }
    public void fill(Function<Vector2,T> func){
        array.fill(func);
    }
    public void clear(){
        array.clear();
    }
    public Array2D<T> resize(int width, int height){
        return array.resize(width,height);
    }
    public Array2D<T> resize(int top, int bottom, int left, int right){
        return array.resize(top,bottom,left,right);
    }
    public int getX(T obj){
        return array.getX(obj);
    }
    public int getY(T obj){
        return array.getY(obj);
    }
    public Vector2 getPos(T obj){
        return array.getPos(obj);
    }
    public int getIdenticalX(T obj){
        return array.getIdenticalX(obj);
    }
    public int getIdenticalY(T obj){
        return array.getIdenticalY(obj);
    }
    public Vector2 getIdenticalPos(T obj){
        return array.getIdenticalPos(obj);
    }

    public int getWidth(){return array.getWidth();}
    public int getHeight(){return array.getHeight();}

    public Array2D<T> getArray() {
        return array;
    }

    @Override
    public void tick() {
        for (Array2D.Array2DEntry<T> entry : array) {
            entry.value.contextAndTick();
        }
    }

    @Override
    public Iterator<Array2D.Array2DEntry<T>> iterator() {
        return new Array2D.Array2DIterator<>(array);
    }
}
