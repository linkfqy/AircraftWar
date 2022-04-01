package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.prop.AbstractProp;

/**
 * @author linkfqy
 */
public abstract class AbstractPropFactory {
    /**
     * 由敌机生成一个道具
     * @param ea 掉落该道具的敌机
     * @return 在敌机的位置上生成的道具
     */
    public abstract AbstractProp create(AbstractEnemy ea);

    /**
     * 由坐标生成一个道具
     * @param locationX 道具的X坐标
     * @param locationY 道具的Y坐标
     * @return 在指定位置生成的道具
     */
    public abstract AbstractProp create(int locationX, int locationY);
}
