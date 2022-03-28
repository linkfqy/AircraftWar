package edu.hitsz.factory;

import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.prop.LifeProp;

public class LifePropFactory extends PropFactory{
    @Override
    public LifeProp create(EnemyAircraft ea) {
        return new LifeProp(ea.getLocationX(),ea.getLocationY());
    }

    @Override
    public LifeProp create(int locationX, int locationY) {
        return new LifeProp(locationX,locationY);
    }
}
