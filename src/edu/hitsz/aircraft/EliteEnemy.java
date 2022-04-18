package edu.hitsz.aircraft;

import edu.hitsz.strategy.EliteShootStrategy;

/**
 * @author linkfqy
 */
public class EliteEnemy extends AbstractEnemy {
    public EliteEnemy (int locationX, int locationY, int speedX, int speedY,int hp){
        super(locationX, locationY, speedX, speedY, hp);
        shootStrategy= new EliteShootStrategy();
    }

    @Override
    public int getScore() {
        return 30;
    }
}
