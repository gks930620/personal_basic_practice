package decorator;

public class Latte  extends  Decorator{
    public Latte(Coffee coffee) {
        super(coffee);
    }

    @Override
    public void brewing() {
        super.brewing();  //원래 데코레이터가 하던 행위
        System.out.println("라떼를 만들려면 우유를 더해라");  //추가로 하는 행위
    }
}
