package decorator;

public class KenyaCoffee extends Coffee{
    @Override
    public void brewing() {
        System.out.println("kenyaAmericano");
    }
}
