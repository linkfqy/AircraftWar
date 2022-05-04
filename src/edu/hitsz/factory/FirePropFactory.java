package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.prop.FireProp;

/**
 * @author linkfqy
 */
public class FirePropFactory extends AbstractPropFactory {
    @Override
    public FireProp pureCreate(AbstractEnemy enemy) {
        return new FireProp(enemy.getLocationX(), enemy.getLocationY());
    }
}
