package edu.hitsz.strategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

/**
 * 英雄机的射击策略Level3
 * 3颗子弹散射，每颗子弹伤害为普通版本的1/2
 * @author linkfqy
 */
public class HeroShootStrategyL3 implements ShootStrategy{
    @Override
    public List<BaseBullet> shoot(AbstractAircraft aircraft) {
        int shootNum=3;
        int x = aircraft.getLocationX();
        // 子弹发射位置相对飞机位置向前偏移
        int y = aircraft.getLocationY() + aircraft.getDirection()*2;
        int vx = 0;
        int vy = aircraft.getSpeedY() + aircraft.getDirection()*20;
        List<BaseBullet> res=new LinkedList<>();
        for(int i=0; i<shootNum; i++){
            // 散射子弹不再横向分布
            res.add(new HeroBullet(
                    x , y,
                    vx + (i*2 - shootNum + 1)*2, vy,
                    aircraft.getPower()/2));
        }
        return res;
    }
}
