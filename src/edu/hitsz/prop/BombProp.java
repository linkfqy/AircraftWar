package edu.hitsz.prop;

import edu.hitsz.aircraft.AbstractEnemy;
import edu.hitsz.basic.AbstractFlyingObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linkfqy
 */
public class BombProp extends AbstractProp {
    public BombProp (int locationX, int locationY, int speedX, int speedY){
        super(locationX,locationY,speedX,speedY);
    }
    public BombProp (int locationX, int locationY){
        super(locationX,locationY);
    }

    private final List<AbstractFlyingObject> subscribers = new ArrayList<>();

    @Override
    public int work() {
        super.work();
        int scoreToAdd=0;
        for (AbstractFlyingObject obj:subscribers) {
            obj.vanish();
            if (obj instanceof AbstractEnemy){
                scoreToAdd+=((AbstractEnemy) obj).getScore();
            }
        }
        return scoreToAdd;
    }

    public void addSubscriber(AbstractFlyingObject flyingObj){
        subscribers.add(flyingObj);
    }
}
