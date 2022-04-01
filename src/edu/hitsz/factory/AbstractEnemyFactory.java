package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractEnemy;

/**
 * @author linkfqy
 */
public abstract class AbstractEnemyFactory {
    /**
     * 创建一架敌机
     * @return 返回随机位置的敌机
     */
    public abstract AbstractEnemy create();
}
