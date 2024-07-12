package templatemethod.gamelevel;

public abstract class PlayerLevel {
    public abstract  void run();
    public abstract  void jump();
    public abstract  void turn();
    public abstract  void showLevelMessage();

    public void fly(){};  //abstract로 하면 반드시 재정의 해줘야 함. 근데 난 super에서만 재정의 하고 싶음 그래서 일단 구현함.
    //이렇게 abstract는 아니지만 어떤것만 재정의 하도록 하는 의도한 메소드 : 훅메소드

    final public void go(int count){  //run jump turn내용은 변해도 이 순서는 변하면 안됨= > 이 go가 템플릿 메소드임
        run();
        for(int i=0  ; i<count ; i++){
            jump();
        }
        turn();

        fly();   //beginner, advanced에서는 아무일도 일어나지 않음.
    }
}
