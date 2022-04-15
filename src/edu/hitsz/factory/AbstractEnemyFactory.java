package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractEnemy;

/**
 * @author linkfqy
 */
public abstract class AbstractEnemyFactory {
    /**
     * 基础血量，简单10，普通15，困难18
     */
    protected static final int BASE_HP=10;
    /**
     * 基础速度，简单5，普通7，困难10
     */
    protected static final int BASE_SPEED=5;
    /**
     * 创建一架敌机
     * @return 返回随机位置的敌机
     */
    public abstract AbstractEnemy create();
}
