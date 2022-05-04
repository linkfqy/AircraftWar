package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.prop.AbstractProp;

import static java.lang.Thread.sleep;

/**
 * @author linkfqy
 */
public abstract class AbstractPropFactory {
    /**
     * 道具消失时间
     */
    private static int timeToVanish = 10_000;
    public static int getTimeToVanish() {
        return timeToVanish;
    }
    public static void setTimeToVanish(int newTimeToVanish) {
        timeToVanish = newTimeToVanish;
    }

    /**
     * 由敌机生成一个道具，并设定其一定时间后自动消失
     * @param enemy 掉落该道具的敌机
     * @return 在敌机的位置上生成的道具
     */
    public final AbstractProp create(AbstractEnemy enemy){
        AbstractProp prop=pureCreate(enemy);
        new Thread(() -> {
            try {
                sleep(timeToVanish);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            prop.vanish();
        }).start();
        return prop;
    }

    /**
     * 由敌机生成一个道具
     * @param enemy 掉落该道具的敌机
     * @return 在敌机的位置上生成的道具
     */
    public abstract AbstractProp pureCreate(AbstractEnemy enemy);
}
