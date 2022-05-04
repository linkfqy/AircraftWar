package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.prop.LifeProp;

/**
 * @author linkfqy
 */
public class LifePropFactory extends AbstractPropFactory {
    @Override
    public LifeProp pureCreate(AbstractEnemy enemy) {
        return new LifeProp(enemy.getLocationX(), enemy.getLocationY());
    }
}
