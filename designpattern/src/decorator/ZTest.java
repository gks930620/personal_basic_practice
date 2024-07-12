package decorator;

import java.io.*;
import java.net.Socket;

public class ZTest {
    public static void main(String[] args) throws Exception {
        Coffee kenyaCoffee=new KenyaCoffee();
        kenyaCoffee.brewing();
        Coffee kenyaLatte=new Latte(kenyaCoffee);  // kenya로 만든 라뗴가 됨
        kenyaLatte.brewing();

        Coffee mochaKenya=new MochaCoffee(new Latte( new KenyaCoffee()));
        mochaKenya.brewing();; //keyna + 라떼 + 모카
        //장식자는 계속 추가될 수 있음.. 추가할 일이 없을 수도 있지만, 반드시 다른애를 포함해야됨
        //맨 마지막에 추가한 애는 실제 객체(etiophia kenya)

        new Latte(new MochaCoffee(new KenyaCoffee()));
        //이러면  케냐 모카 라떼
        


        Socket socket=new Socket();
        BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //BufferedReader, InpustreamReader가 생성자는 다를 수 있음.
        //BufferedReader는 데코레이터 InputStreamReader을 받아 그 기능을 수행하고 추가적으로 buffer기능까지수행함
        //데코레이터 InpustreamReader가 또 다른 데코레이터를 장식할 거라는 보장은 없음.

        br.readLine();

        new BufferedReader(new FileReader("ss"));
        new BufferedReader(new InputStreamReader(System.in));
        new BufferedReader(new InputStreamReader(new FileInputStream("file")));
    }

}
