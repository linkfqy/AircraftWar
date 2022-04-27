package edu.hitsz.prop;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.strategy.*;

import static java.lang.Thread.sleep;

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
        //火力等级增大一级
        ShootStrategy strategy=aircraft.getShootStrategy();
        if (strategy instanceof HeroShootStrategyL1){
            aircraft.setShootStrategy(new HeroShootStrategyL3());
        }else if (strategy instanceof HeroShootStrategyL3){
            aircraft.setShootStrategy(new HeroShootStrategyL5());
        }
        //等待10秒后减小火力
        Runnable sleepAndReduce = ()->{
            try {
                sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //火力等级减小一级
            ShootStrategy strategy2=aircraft.getShootStrategy();
            if (strategy2 instanceof HeroShootStrategyL5){
                aircraft.setShootStrategy(new HeroShootStrategyL3());
            }else if (strategy2 instanceof HeroShootStrategyL3){
                aircraft.setShootStrategy(new HeroShootStrategyL1());
            }
        };
        new Thread(sleepAndReduce).start();
    }
}
