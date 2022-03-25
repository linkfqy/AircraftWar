package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;

import java.util.LinkedList;
import java.util.List;

/**
 * 普通敌机
 * 不可射击
 *
 * @author hitsz
 */
public class MobEnemy extends EnemyAircraft {
    public MobEnemy(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY, 30);
    }


    @Override
    public List<BaseBullet> shoot() {
        return new LinkedList<>();
    }

}
