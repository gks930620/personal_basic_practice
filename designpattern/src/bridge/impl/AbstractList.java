package bridge.impl;

public interface AbstractList<T> {
    //실제 기능은 이걸상속받은 무언가인가?
    public  void addElement(T obj);
    public  int insertElement(T obj,int i);

    public  T deleteElement(int i );
    public  T getElement(int i);
    public  int getElementSize();



}
