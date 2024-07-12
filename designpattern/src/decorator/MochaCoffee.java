package decorator;

public class MochaCoffee extends  Decorator{
    public MochaCoffee(Coffee coffee) {
        super(coffee);
    }

    @Override
    public void brewing() {
        super.brewing();
        System.out.println("모카시럽을 추가해야됨");
    }
}
