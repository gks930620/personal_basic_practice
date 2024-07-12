package factorymethod;

public class Main {
    public static void main(String[] args) {
        CarFactory factory=new HundaiCarFactory();
        Car newCar= factory.createCar("sonata");  //createCar 파라미터에 따라서 생성이 알아서 잘됨

        System.out.println(newCar.carType);

        Car tomasCar=factory.returnCar("Tomas");
        Car tomasCar2=factory.returnCar("Tomas");
        System.out.println(tomasCar2==tomasCar);

    }
}
