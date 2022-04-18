package edu.hitsz.prop;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.strategy.*;

/**
 * @author linkfqy
 */
public class FireProp extends AbstractProp {
    public FireProp (int locationX, int locationY, int speedX, int speedY){
        super(locationX,locationY,speedX,speedY);
    }
    public FireProp (int locationX, int locationY){
        super(locationX,locationY);
    }

    @Override
    public void work(AbstractAircraft aircraft) {
        super.work(aircraft);
        ShootStrategy strategy=aircraft.getShootStrategy();
        if (strategy instanceof HeroShootStrategyL1){
            aircraft.setShootStrategy(new HeroShootStrategyL3());
        }else if (strategy instanceof HeroShootStrategyL3){
            aircraft.setShootStrategy(new HeroShootStrategyL5());
        }
    }
}
