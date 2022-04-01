package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;

import java.util.LinkedList;
import java.util.List;

/**
 * @author linkfqy
 */
public class EliteEnemy extends AbstractEnemy {
    private int direction=1;
    private int power=30;
    public EliteEnemy (int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY, 50);
    }

    @Override
    public List<BaseBullet> shoot() {
        int x = this.getLocationX();
        int y = this.getLocationY();
        int vx = 0;
        int vy = this.getSpeedY() + direction*5;
        List<BaseBullet> res=new LinkedList<>();
        res.add(new EnemyBullet(x,y,vx,vy,power));
        return res;
    }
}
