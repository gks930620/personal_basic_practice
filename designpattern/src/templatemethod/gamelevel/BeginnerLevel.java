package templatemethod.gamelevel;

public class BeginnerLevel extends PlayerLevel{
    @Override
    public void run() {
        System.out.println("천천히 달림");
    }

    @Override
    public void jump() {
        System.out.println("점프 못함");
    }

    @Override
    public void turn() {
        System.out.println("Turn도 할 줄 모름");
    }

    @Override
    public void showLevelMessage() {
        System.out.println("초보자 레벨 입니당!!");
    }
    //go는 final이기 때문에 오버라이딩 X
}
