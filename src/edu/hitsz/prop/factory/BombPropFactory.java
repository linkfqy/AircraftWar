package edu.hitsz.prop.factory;

import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.prop.BombProp;

/**
 * @author linkfqy
 */
public class BombPropFactory extends AbstractPropFactory {
    @Override
    public BombProp pureCreate(AbstractEnemy enemy) {
        return new BombProp(enemy.getLocationX(), enemy.getLocationY());
    }
}
