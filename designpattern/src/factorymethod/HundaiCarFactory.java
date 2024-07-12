package factorymethod;

import java.util.HashMap;

public class HundaiCarFactory extends  CarFactory{
    //factory가 생성도 하지만 여러개 생성안하고 이미 있는거를 제공해주는 기능일 때도 있음
    HashMap<String , Car> carMap=new HashMap<>();


    @Override
    public Car createCar(String name) {
        Car car=null;
        if(name.equals("sonata")){
            car =new Sonata();
        }else if(name.equals("santafe")){
            car=new Santafe();
        }
        return car;
    }
    //차가 이미 있으면 해다  instance를 반환함.
    public Car returnCar(String name){
        Car car=carMap.get(name);
        if(car==null){
            if(name.equals("Tomas")){
                car=new Sonata();
            }else if(name.equals("James")){
                car=new Santafe();
            }
            carMap.put(name,car);
        }
        return car;
    }

}
