package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.prop.BombProp;

/**
 * @author linkfqy
 */
public class BombPropFactory extends AbstractPropFactory {
    @Override
    public BombProp create(AbstractEnemy ea) {
        return new BombProp(ea.getLocationX(),ea.getLocationY());
    }

    @Override
    public BombProp create(int locationX, int locationY) {
        return new BombProp(locationX,locationY);
    }
}
