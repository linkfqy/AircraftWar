package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.prop.LifeProp;

/**
 * @author linkfqy
 */
public class LifePropFactory extends AbstractPropFactory {
    @Override
    public LifeProp create(AbstractEnemy ea) {
        return new LifeProp(ea.getLocationX(),ea.getLocationY());
    }

    @Override
    public LifeProp create(int locationX, int locationY) {
        return new LifeProp(locationX,locationY);
    }
}
