package templatemethod;

import templatemethod.gamelevel.BeginnerLevel;
import templatemethod.gamelevel.PlayerLevel;

public class Player {
    private PlayerLevel level;
    
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
