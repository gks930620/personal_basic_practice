package adapter;

//가장 좋은 예는 안드로이드의 ListView, Item, Adapter 임.

public class AdapterTest  {
    public static void main(String[] args) {
        Print print=new PrintBanner("hello");
        print.printWeek();
        print.printStrong();

        //클라이언트는 adapter를 바꾸면서.  PrintBanner2,3,4...를 사용하지만
        //원하는 기능(printweek,printStrong)은 그대로 가져감.

        // PrintBanner2,3,4는 같은 Banner를 사용할 수도 있고 아닐 수도 있고
        // 어쨋든 다양한 기능을 제공해줄 수 있음.
        //client의 코드는 변경이 적음




    }
}
