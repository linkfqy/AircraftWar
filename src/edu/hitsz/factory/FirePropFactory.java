package edu.hitsz.factory;

import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.prop.FireProp;

public class FirePropFactory extends PropFactory{
    @Override
    public FireProp create(EnemyAircraft ea) {
        return new FireProp(ea.getLocationX(),ea.getLocationY());
    }

    @Override
    public FireProp create(int locationX, int locationY) {
        return new FireProp(locationX,locationY);
    }
}
