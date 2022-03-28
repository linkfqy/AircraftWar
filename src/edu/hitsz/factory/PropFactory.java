package edu.hitsz.factory;

import edu.hitsz.aircraft.EnemyAircraft;
import edu.hitsz.prop.AbstractProp;

public abstract class PropFactory {
    public abstract AbstractProp create(EnemyAircraft ea);
    public abstract AbstractProp create(int locationX, int locationY);
}
