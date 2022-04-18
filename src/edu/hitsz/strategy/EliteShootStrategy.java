package edu.hitsz.strategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;

import java.util.LinkedList;
import java.util.List;

/**
 * 精英机发射子弹的策略
 * 弹速：vx=0, vy=v+d*5
 * @author linkfqy
 */
public class EliteShootStrategy implements ShootStrategy{
    @Override
    public List<BaseBullet> shoot(AbstractAircraft aircraft) {
        int x = aircraft.getLocationX();
        // 子弹发射位置相对飞机位置向前偏移
        int y = aircraft.getLocationY() + aircraft.getDirection()*2;
        int vx = 0;
        int vy = aircraft.getSpeedY() + aircraft.getDirection()*5;
        List<BaseBullet> res=new LinkedList<>();
        res.add(new EnemyBullet(x,y,vx,vy,aircraft.getPower()));
        return res;
    }
}
