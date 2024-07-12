package decorator;

public abstract  class Decorator extends Coffee{
    //decorator가 직접 생성될 일이 없기 때문에 추상클래스

    Coffee coffee;  // decorator는 혼자서는 아무것도 못하고 실제 커피객체를 관리할 뿐임

    public Decorator(Coffee coffee) {  //decorator를 만들떄
        //또다른 decorator거나 실제 Coffee객체(etiopia or kenya)
        this.coffee = coffee;
    }

    @Override
    public void brewing() {
        coffee.brewing();  //데코레이터일뿐아무 기능이 없다.
    }


}
