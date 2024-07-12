package bridge.impl;

import bridge.list.List;

import java.util.ArrayList;

public class ArrayListImpl<T> implements AbstractList<T> {
    ArrayList<T> array;

    public ArrayListImpl() {
        array=new ArrayList<T>();
    }

    @Override
    public void addElement(T obj) {
        array.add(obj);
    }
    @Override
    public int insertElement(T obj, int i) {
        array.add(i,obj);
        return i;
    }
    @Override
    public T deleteElement(int i) {
        return array.remove(i);
    }
    @Override
    public T getElement(int i) {
        return array.get(i);
    }
    @Override
    public int getElementSize() {
        return array.size();
    }
}
