package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.prop.FireProp;

/**
 * @author linkfqy
 */
public class FirePropFactory extends AbstractPropFactory {
    @Override
    public FireProp create(AbstractEnemy ea) {
        return new FireProp(ea.getLocationX(),ea.getLocationY());
    }

    @Override
    public FireProp create(int locationX, int locationY) {
        return new FireProp(locationX,locationY);
    }
}
