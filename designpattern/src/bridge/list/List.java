package bridge.list;

import bridge.impl.AbstractList;

public abstract class List<T> {
    //애는 구현없음 기능만 정의한거.  클라이언트는 List만 사용함.AbstractList사용X
    AbstractList<T> impl;

    public List(AbstractList<T> list){
        impl=list;
    }

    public void add(T obj){
        impl.addElement(obj);
    }
    
    public T remove(int i){
        return impl.deleteElement(i);
    }


    public T get(int i){
        return impl.getElement(i);
    }
    public int getSize(){
        return impl.getElementSize();
    }


    
}
