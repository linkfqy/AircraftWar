package edu.hitsz.aircraft;

import edu.hitsz.strategy.EmptyShootStrategy;

/**
 * 普通敌机
 * 不可射击
 *
 * @author hitsz
 */
public class MobEnemy extends AbstractEnemy {
    public MobEnemy(int locationX, int locationY, int speedX, int speedY,int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        shootStrategy=new EmptyShootStrategy();
    }

    @Override
    public int getScore() {
        return 10;
    }
}
