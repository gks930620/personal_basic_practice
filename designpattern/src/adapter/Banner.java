package adapter;

public class Banner {  //adaptee   실제 일하는애
    private String string;
    public Banner(String string) {
        this.string = string;
    }

    public void showWithParen(){
        System.out.println("(" + string + ")");   //print의 week
    }
    public void showWithAster(){
        System.out.println("**" + string + "**");  //print의 strong
    }

    //Banner의 코드는 바뀔 수 없음.  이미 다른곳에서 많이 사용하는 중
    // 같은 기능을 week, strong으로 사용하고 싶음

}
