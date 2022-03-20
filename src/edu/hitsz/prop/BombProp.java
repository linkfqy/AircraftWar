package edu.hitsz.prop;

import edu.hitsz.aircraft.AbstractAircraft;

public class BombProp extends AbstractProp {
    public BombProp (int locationX, int locationY, int speedX, int speedY){
        super(locationX,locationY,speedX,speedY);
    }
    public BombProp (int locationX, int locationY){
        super(locationX,locationY);
    }

    @Override
    public void work(AbstractAircraft aircraft) {
        super.work(aircraft);
        System.out.println("BombSupply active!");
    }
}
