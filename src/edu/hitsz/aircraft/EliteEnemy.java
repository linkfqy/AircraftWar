package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;

import java.util.LinkedList;
import java.util.List;

/**
 * @author linkfqy
 */
public class EliteEnemy extends AbstractEnemy {
    public EliteEnemy (int locationX, int locationY, int speedX, int speedY,int hp){
        super(locationX, locationY, speedX, speedY, hp);
    }

    @Override
    public List<BaseBullet> shoot() {
        int x = this.getLocationX();
        int y = this.getLocationY();
        int vx = 0;
        int vy = this.getSpeedY() + DIRECTION *5;
        List<BaseBullet> res=new LinkedList<>();
        res.add(new EnemyBullet(x,y,vx,vy,power));
        return res;
    }

    @Override
    public int getScore() {
        return 30;
    }
}
