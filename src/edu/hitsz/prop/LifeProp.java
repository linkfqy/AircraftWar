package edu.hitsz.prop;

import edu.hitsz.aircraft.AbstractAircraft;

/**
 * @author linkfqy
 */
public class LifeProp extends AbstractProp {
    /** 默认加血量 */
    int incHp=50;

    public LifeProp (int locationX, int locationY, int speedX, int speedY){
        super(locationX,locationY,speedX,speedY);
    }
    public LifeProp (int locationX, int locationY){
        super(locationX,locationY);
    }

    @Override
    public void work(AbstractAircraft aircraft) {
        super.work(aircraft);
        aircraft.increaseHp(incHp);
    }
}
