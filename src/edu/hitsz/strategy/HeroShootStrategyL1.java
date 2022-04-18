package edu.hitsz.strategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

/**
 * 英雄机的射击策略Level1
 * 单颗子弹直射
 * 弹速：vx=0, vy=y+d*20
 * @author linkfqy
 */
public class HeroShootStrategyL1 implements ShootStrategy{
    @Override
    public List<BaseBullet> shoot(AbstractAircraft aircraft) {
        int x = aircraft.getLocationX();
        // 子弹发射位置相对飞机位置向前偏移
        int y = aircraft.getLocationY() + aircraft.getDirection()*2;
        int vx = 0;
        int vy = aircraft.getSpeedY() + aircraft.getDirection()*20;
        List<BaseBullet> res=new LinkedList<>();
        res.add(new HeroBullet(x,y,vx,vy, aircraft.getPower()));
        return res;
    }
}
