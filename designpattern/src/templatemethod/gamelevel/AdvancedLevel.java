package templatemethod.gamelevel;

public class AdvancedLevel extends PlayerLevel{
    @Override
    public void run() {
        System.out.println("보통속도로 달림");
    }

    @Override
    public void jump() {
        System.out.println("점프 함");
    }

    @Override
    public void turn() {
        System.out.println("Turn도 함");
    }

    @Override
    public void showLevelMessage() {
        System.out.println("AdvancedL 레벨 입니당!!");
    }
}
