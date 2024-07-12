package bridge.impl;


import java.util.LinkedList;

public class LinkedListImpl<T> implements AbstractList<T> {
    //원래는 직접만들으는 거고 필드는 없는데 직접 만들기 귀찮으니까  util거 사용
    LinkedList<T> linkedList;

    public LinkedListImpl() {
        this.linkedList = new LinkedList<>();
    }

    @Override
    public void addElement(T obj) {
        linkedList.add(obj);
    }
    @Override
    public int insertElement(T obj, int i) {
        linkedList.add(i,obj);
        return i;
    }
    @Override
    public T deleteElement(int i) {
        return linkedList.remove(i);
    }
    @Override
    public T getElement(int i) {
        return linkedList.get(i);
    }
    @Override
    public int getElementSize() {
        return linkedList.size();
    }

}
