package edu.hitsz.aircraft;

import edu.hitsz.application.Main;

/**
 * @author linkfqy
 */
public abstract class AbstractEnemy extends AbstractAircraft{
    public AbstractEnemy(int locationX, int locationY, int speedX, int speedY, int hp){
        super(locationX, locationY, speedX, speedY, hp);
    }

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }
    }
}
