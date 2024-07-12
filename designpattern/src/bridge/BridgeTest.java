package bridge;

import bridge.impl.ArrayListImpl;
import bridge.impl.LinkedListImpl;
import bridge.list.Queue;
import bridge.list.Stack;

public class BridgeTest {
    //bridge패턴인데 기능과 구현을 분리함
    //기능 구현의 결합이 약함
    //구현이 변경되도 기능 클래스 부분에 영향X
    // 클라이언트는 기능의 인터페이스를 사용, 구현내용은 알 필요없다( List면 list지 무슨 List인지는 무관심)
    //예제는 list패키지가 기능,  impl패키지가 구현
    public static void main(String[] args) {
        Stack<String> linkedListStack=new Stack<>(new LinkedListImpl<>());
        linkedListStack.push("aaa");
        linkedListStack.push("bbb");
        linkedListStack.push("ccc");
        System.out.println(linkedListStack.pop());
        System.out.println(linkedListStack.pop());
        System.out.println(linkedListStack.pop());
        System.out.println("-------------------------");
        Stack<String > arrayStack=new Stack<>(new ArrayListImpl<>());
        arrayStack.push("aaa");
        arrayStack.push("bbb");
        arrayStack.push("ccc");
        System.out.println("-------------------------");
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
        System.out.println("-------------------------");
        Queue<String> linkedListQueue=new Queue<>(new ArrayListImpl<>());
        linkedListQueue.enQueue("aaa");
        linkedListQueue.enQueue("bbb");
        linkedListQueue.enQueue("ccc");
        System.out.println(linkedListQueue.deQueue());
        System.out.println(linkedListQueue.deQueue());
        System.out.println(linkedListQueue.deQueue());
        System.out.println("-------------------------");
        Queue<String> arrayQueue=new Queue<>(new ArrayListImpl<>());
        arrayQueue.enQueue("aaa");
        arrayQueue.enQueue("bbb");
        arrayQueue.enQueue("ccc");
        System.out.println(arrayQueue.deQueue());
        System.out.println(arrayQueue.deQueue());
        System.out.println(arrayQueue.deQueue());
        System.out.println("-------------------------");







    }

}
