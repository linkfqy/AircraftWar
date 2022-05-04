package edu.hitsz.aircraft.factory;

import edu.hitsz.aircraft.AbstractEnemy;

/**
 * @author linkfqy
 */
public abstract class AbstractEnemyFactory {
    /**
     * 基础血量，具体敌机血量由此计算而来
     */
    protected double baseHp =30.0;
    /**
     * 基础速度
     */
    protected static int baseSpeed =5;

    /**
     * 创建一架敌机
     * @return 返回随机位置的敌机
     */
    public abstract AbstractEnemy create();

    /**
     * 获取具体敌机的血量
     * @return 子类中具体敌机的血量
     */
    public abstract int getHp();

    public double getBaseHp() {
        return baseHp;
    }
    public void setBaseHp(double baseHp) {
        this.baseHp = baseHp;
    }

    public static int getBaseSpeed() {
        return baseSpeed;
    }
    public static void setBaseSpeed(int newBaseSpeed) {
        baseSpeed = newBaseSpeed;
    }
}
