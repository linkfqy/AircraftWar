package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;

import java.util.LinkedList;
import java.util.List;

public class EliteEnemy extends AbstractAircraft{
    int direction=1;
    int power=30;
    public EliteEnemy (int locationX, int locationY, int speedX, int speedY, int hp){
        super(locationX, locationY, speedX, speedY, hp);
    }
    public EliteEnemy (int locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY, 50);
    }

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }
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
