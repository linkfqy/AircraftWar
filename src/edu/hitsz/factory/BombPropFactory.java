package edu.hitsz.factory;

import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.prop.BombProp;

public class BombPropFactory extends PropFactory{
    @Override
    public BombProp create(EnemyAircraft ea) {
        return new BombProp(ea.getLocationX(),ea.getLocationY());
    }

    @Override
    public BombProp create(int locationX, int locationY) {
        return new BombProp(locationX,locationY);
    }
}
