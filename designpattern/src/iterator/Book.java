package iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Book {
    private String name="";

    public Book(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void test(){
        List<String> list=new LinkedList<>();
        Iterator<String> ir=list.iterator();
        //Iterator하나로 list, set를 전부 순회할 수 있음
        if(ir.hasNext()){

        }
    }

}
