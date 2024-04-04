package me.carina.rpg.common.util;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.ReflectionPool;
import me.carina.rpg.Game;

import java.util.Iterator;
import java.util.function.Supplier;

/**
 * Double-linked red-black binary tree that uses int provided upon insertion to keep it sorted.
 * Supports multiple values with same index, iterated with insertion order.
 * Uses Pool to avoid allocation for nodes and values.
 * @param <T>
 */
public class SortedArray<T> implements Iterable<T>{
    static final int BLACK = 0, RED = 1;

    static SortedArray<Ts> s = new SortedArray<>(Ts::new);
    public static void addAll(){
        s.add(10).s = "10";
        s.add(5).s = "5";
        s.add(2).s = "2";
        s.add(4).s = "4";
        s.add(6).s = "6";
        s.add(7).s = "7";
        s.add(1).s = "1";
        s.add(10).s = "10";
        for (Ts ts : s) {
            Game.getInstance().getLogger().debug(ts.s);
        }
    }

    Node<T> root;
    Node<T> current;
    Pool<Node<T>> nodePool;
    Pool<T> valuePool;
    int size;
    public SortedArray(Supplier<T> initializer){
        nodePool = new Pool<Node<T>>() {
            @Override
            protected Node<T> newObject() {
                return new Node<>();
            }
        };
        valuePool = new Pool<T>() {
            @Override
            protected T newObject() {
                return initializer.get();
            }
        };
    }
    public static class Ts{
        String s;
    }

    /**
     * Insert a new value at specified index
     * @param index Index of a new value to be created at
     * @return New instance of T, which was inserted
     */
    public T add(int index){
        size++;
        //if tree is empty, create new node and assign it to root as black
        if (root == null){
            root = insertRoot(index,BLACK);
            return root.value;
        }
        //if not empty, binary search where the node should be inserted
        else {
            current = root;
            while (true){
                if (current.index > index){
                    if (current.leftNode == null){
                        current = insertLeft(current,index,RED);
                        break;
                    }
                    current = current.leftNode;
                }
                else {
                    if (current.rightNode == null){
                        current = insertRight(current,index,RED);
                        break;
                    }
                    current = current.rightNode;
                }
            }
            //if parent is black, all is fine
            if (current.parentNode.color == BLACK) return current.value;
            //if parent is red, red collision occurs
            colorCheck(current.parentNode);
            return current.value;
        }
    }
    private void colorCheck(Node<T> node){
        //if brother is red, swap color with parent (guaranteed to be black)
        Node<T> brother = node.getBrother();
        if (brother != null && brother.color == RED){
            colorSwap(node.parentNode);
            colorCheck(node.parentNode);
        }
        else {
            rotate(node);
        }
    }
    private void colorSwap(Node<T> node){
        int color = node.color;
        if (node.leftNode != null) node.leftNode.color = color;
        node.color = 1-color;
        if (node.rightNode != null) node.rightNode.color = color;
    }

    private void rotate(Node<T> node){
        if (node.parentNode == null) return;
        if (node.parentNode.leftNode == node){
            //if right node is red, left rotate so that left becomes red
            if (node.rightNode != null && node.rightNode.color == RED) node = rotateLeft(node);
            //rotate right
            node = rotateRight(node.parentNode);
            node.color = BLACK;
            if (node.rightNode != null) node.rightNode.color = RED;
        }
        else if (node.parentNode.rightNode == node){
            //if left node is red, right rotate so that right becomes red
            if (node.leftNode != null && node.leftNode.color == RED) node = rotateRight(node);
            //rotate left
            node = rotateLeft(node.parentNode);
            node.color = BLACK;
            if (node.leftNode != null) node.leftNode.color = RED;
        }
    }
    private Node<T> rotateRight(Node<T> node){
        node.leftNode.parentNode = node.parentNode;
        node.parentNode = node.leftNode;
        node.leftNode = node.parentNode.rightNode;
        if (node.leftNode != null) node.leftNode.parentNode = node;
        node.parentNode.rightNode = node;
        if (node.parentNode.parentNode == null) root = node.parentNode;
        else if (node.parentNode.parentNode.leftNode == node) node.parentNode.parentNode.leftNode = node.parentNode;
        else node.parentNode.parentNode.rightNode = node.parentNode;
        return node.parentNode;
    }
    private Node<T> rotateLeft(Node<T> node){
        node.rightNode.parentNode = node.parentNode;
        node.parentNode = node.rightNode;
        node.rightNode = node.parentNode.leftNode;
        if (node.rightNode != null) node.rightNode.parentNode = node;
        node.parentNode.leftNode = node;
        if (node.parentNode.parentNode == null) root = node.parentNode;
        else if (node.parentNode.parentNode.leftNode == node) node.parentNode.parentNode.leftNode = node.parentNode;
        else node.parentNode.parentNode.rightNode = node.parentNode;
        return node.parentNode;
    }
    private T newValue(){
        return valuePool.obtain();
    }
    private Node<T> insertRoot(int index, int color){
        Node<T> node = nodePool.obtain();
        node.value = newValue();
        node.index = index;
        node.color = color;
        root = node;
        return node;
    }
    private Node<T> insertLeft(Node<T> parent, int index, int color){
        Node<T> node = nodePool.obtain();
        node.value = newValue();
        node.index = index;
        node.color = color;
        node.parentNode = parent;
        parent.leftNode = node;
        return node;
    }
    private Node<T> insertRight(Node<T> parent, int index, int color){
        Node<T> node = nodePool.obtain();
        node.value = newValue();
        node.index = index;
        node.color = color;
        node.parentNode = parent;
        parent.rightNode = node;
        return node;
    }

    @Override
    public Iterator<T> iterator() {
        return new SortedArrayIterator<>(this);
    }

    public static class Node<T> {
        Node<T> leftNode, rightNode, parentNode;
        int index;
        int color;
        T value;

        public Node() {
        }
        public Node<T> getBrother(){
            if (parentNode == null) return null;
            return parentNode.leftNode == this ? parentNode.rightNode : parentNode.leftNode;
        }
    }

    public static class SortedArrayIterator<T> implements Iterator<T>{
        SortedArray<T> array;
        Node<T> node;
        Node<T> prevNode;
        int index;
        public SortedArrayIterator(SortedArray<T> array){
            this.array = array;
            node = array.root;
            prevNode = null;
            index = 0;
        }
        @Override
        public boolean hasNext() {
            return index++ < array.size;
        }

        @Override
        public T next() {
            while (true){
                //Going down from parent
                if (node.parentNode == prevNode){
                    //Left node is not found, return itself & assign right node to be traversed
                    if (node.leftNode == null){
                        prevNode = node;
                        return node.value;
                    }
                    //Left node found, go deeper
                    else {
                        prevNode = node;
                        node = node.leftNode;
                    }
                }
                //just returned itself, traverse right nodes
                else if (node == prevNode){
                    //Right node not found, bubble up
                    if (node.rightNode == null){
                        node = node.parentNode;
                    }
                    //Right node found, go deeper
                    else {
                        node = node.rightNode;
                    }
                }
                //Bubbling up from left
                else if (node.leftNode == prevNode){
                    //return itself and & assign right node to be traversed
                    prevNode = node;
                    return node.value;
                }
                //Bubbling up from right
                else if (node.rightNode == prevNode) {
                    //Bubble up further
                    prevNode = node;
                    node = node.parentNode;
                }
            }
        }
    }
}
