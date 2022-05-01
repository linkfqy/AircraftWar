package edu.hitsz.prop;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.application.Main;
import edu.hitsz.basic.AbstractFlyingObject;

/**
 * 抽象道具类。
 * 提供了两种生效的方式，子类必须实现其中之一
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
     */
    public void work(AbstractAircraft aircraft){
        // 道具生效后必须销毁道具
        vanish();
    }

    /**
     * 道具生效后产生加分
     */
    public int work(){
        // 道具生效后必须销毁道具
        vanish();
        return 0;
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
