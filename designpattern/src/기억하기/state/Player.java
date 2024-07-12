package 기억하기.state;

import 기억하기.state.gamelevel.BeginnerLevel;
import 기억하기.state.gamelevel.PlayerLevel;

public class Player {
    private PlayerLevel level;  //이런 level이 없다면 if else 해야지
    
    public  Player(){
        level=new BeginnerLevel(); //시작은 begiiner로
        level.showLevelMessage();
    }

    public void upgradeLevel(PlayerLevel level){
        this.level=level;
    }
    public void play(int count){
        level.go(count);
    }

    public PlayerLevel getPlayerLevel(){
        return level;
    }

}
