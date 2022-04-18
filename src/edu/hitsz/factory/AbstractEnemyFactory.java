package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractEnemy;

/**
 * @author linkfqy
 */
public abstract class AbstractEnemyFactory {
    /**
     * 基础血量，简单30，普通45，困难54
     */
    protected static final int BASE_HP=30;
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
