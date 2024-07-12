package 기억하기.observer;

import java.util.Random;

public class RandomNumberGenerator extends  NumberGenerator{
    private  int number;
    private Random random=new Random();
    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public void excute() {
        for(int i=0 ; i<50 ; i++){
            number=random.nextInt(50);
            notifyObservers();
        }
    }
}
