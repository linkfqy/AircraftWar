package edu.hitsz.aircraft;

import edu.hitsz.application.Main;

/**
 * @author linkfqy
 */
public abstract class AbstractEnemy extends AbstractAircraft{
    protected static final int DIRECTION=1;
    protected int power=10;
    public AbstractEnemy(int locationX, int locationY, int speedX, int speedY, int hp){
        super(locationX, locationY, speedX, speedY, hp);
    }

    @Override
    public void forward() {
        super.forward();
        if (locationX <= 0 || locationX >= Main.WINDOW_WIDTH) {
            // 横向超出边界后反向
            speedX = -speedX;
        }
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }
    }

    /**
     * 击毁后加分
     * @return 返回击毁后加的分值
     */
    public abstract int getScore();
}
