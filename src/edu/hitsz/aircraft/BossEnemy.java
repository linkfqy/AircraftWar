package edu.hitsz.aircraft;

import edu.hitsz.strategy.BossShootStrategy;

/**
 * @author linkfqy
 */
public class BossEnemy extends AbstractEnemy {
    public BossEnemy(int locationX, int locationY, int speedX, int speedY,int hp){
        super(locationX, locationY, speedX, speedY, hp);
        power*=3;
        shootStrategy=new BossShootStrategy();
    }

    @Override
    public int getScore() {
        return 70;
    }
}
