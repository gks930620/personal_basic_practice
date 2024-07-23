package com.example.basicenvironmentboot3;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int max=100;     // 100
        int min=-100;    //-100,  일때는 문제없음.
        int ten=10;
        int minusTen=-10;
        int zero=0;
        List<Integer> list= new ArrayList<>();
        list.add(max);
        list.add(min);
        list.add(ten);
        list.add(minusTen);
        list.add(zero);
        Collections.sort(list , (o1,o2)->{return o1-o2;});    //오름차순
        // 12억 -(-12억)하면  양수가나오면  정렬 -12억,12억 순인데
        // 이게 자바에서는 음수가 나와  정렬이 반대로 되버림..      이게 꼬임.
        // 그래서 comparable할때 대충 o1-o2만 하면 대부분 상관없지만 값이 오버플로우넘어갈 때 문제가생김
        // 그래서 원칙은 if o1>o2 return -1  o1==o2 return 0   else 1    오름차순, 내림차순은 1,-1위치만바꾸면됨
        System.out.println(list);


    }
}
