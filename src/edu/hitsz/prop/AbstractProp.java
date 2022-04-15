package edu.hitsz.prop;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.application.Main;
import edu.hitsz.basic.AbstractFlyingObject;

/**
 * @author linkfqy
 */
public abstract class AbstractProp extends AbstractFlyingObject {
    public AbstractProp(int locationX, int locationY, int speedX, int speedY){
        super(locationX,locationY,speedX,speedY);
    }
    public AbstractProp(int locationX, int locationY){
        super(locationX,locationY,(Main.RAND.nextInt(2)*2-1)*5,5);
    }

    /**
     * 道具作用于英雄机
     * 作用后必须销毁道具
     */
    public void work(AbstractAircraft aircraft){
        vanish();
    }

    @Override
    public void forward() {
        super.forward();
        if (locationX <= 0 || locationX+this.getWidth() >= Main.WINDOW_WIDTH) {
            // 横向超出边界后反向
            speedX = -speedX;
        }
        if (locationY <= 0 || locationY+this.getHeight() >= Main.WINDOW_HEIGHT) {
            // 纵向超出边界后反向
            speedY = -speedY;
        }
    }
}
