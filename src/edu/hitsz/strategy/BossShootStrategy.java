package edu.hitsz.strategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;

import java.util.LinkedList;
import java.util.List;

/**
 * Boss机的射击策略
 * 9颗散射，伤害是精英机的3倍
 * @author linkfqy
 */
public class BossShootStrategy implements ShootStrategy{
    @Override
    public List<BaseBullet> shoot(AbstractAircraft aircraft) {
        //9颗子弹，7颗散射，2颗直射
        int shootNum=7;
        int x = aircraft.getLocationX();
        // 子弹发射位置相对飞机位置向前偏移
        int y = aircraft.getLocationY() + aircraft.getDirection()*2;
        int vx = aircraft.getSpeedX();
        int vy = aircraft.getSpeedY() + aircraft.getDirection()*5;
        List<BaseBullet> res=new LinkedList<>();
        for(int i=0; i<shootNum; i++){
            // 散射子弹不再横向分布
            res.add(new EnemyBullet(
                    x , y,
                    vx + (i*2 - shootNum + 1), vy,
                    aircraft.getPower()));
        }
        for(int i=0; i<2; i++){
            // 直射子弹需要横向分布
            res.add(new EnemyBullet(
                    x + (i*2 - 1)*30, y,
                    vx , vy,
                    aircraft.getPower()));
        }
        return res;
    }
}
