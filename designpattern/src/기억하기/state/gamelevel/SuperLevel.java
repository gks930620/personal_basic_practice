package 기억하기.state.gamelevel;

public class SuperLevel extends PlayerLevel {
    @Override
    public void run() {
        System.out.println("아주빠른 속도로 달림");
    }

    @Override
    public void jump() {
        System.out.println("점프 높함");
    }

    @Override
    public void turn() {
        System.out.println("Turn도 스무스하게 함");
    }

    @Override
    public void showLevelMessage() {
        System.out.println("Super 레벨 입니당!!");
    }


    @Override
    public void fly(){
        System.out.println("뛰다 못해 난다.");
    }
}
