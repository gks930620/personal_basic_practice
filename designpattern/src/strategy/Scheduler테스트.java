package strategy;

import java.io.IOException;

public class Scheduler테스트 {
    public static void main(String[] args) throws IOException {
        int ch=System.in.read();
        Scheduler scheduler=null;
        if( ch=='r' || ch=='R'){
            scheduler=new RoundRobin();
        } else if (ch=='L'){
            scheduler=new LeastJob();
        }else{
            scheduler=new PriorityAction();   //인터페이스의 기본에서 배우는것이자 전략패턴
        }
        scheduler.getNextCall();
        scheduler.sendCallToAgent();

        // 여기서 무언가 Scheduler를 더 만들어야 되면 다른 Scheduler에는 영향 안 미치면서 추가 가능
    }
}
