package templatemethod;

import templatemethod.gamelevel.AdvancedLevel;
import templatemethod.gamelevel.SuperLevel;

public class Main {
    public static void main(String[] args) {
        Player player=new Player();
        player.play(3);

        player.upgradeLevel(new AdvancedLevel());
        player.play(2);

        player.upgradeLevel(new SuperLevel());
        player.play(2);

    }
}
