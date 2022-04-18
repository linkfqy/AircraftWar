package edu.hitsz.strategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;

import java.util.List;

/**
 * @author linkfqy
 */
public interface ShootStrategy {
    /**
     * 执行相应的射击策略
     * @param aircraft 射出子弹的飞机
     * @return 返回子弹列表
     */
    List<BaseBullet> shoot(AbstractAircraft aircraft);
}
