package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.strategy.HeroShootStrategyL1;


/**
 * 英雄飞机，游戏玩家操控
 * @author hitsz
 */
public class HeroAircraft extends AbstractAircraft {

    /** 单例模式的唯一实例*/
    private volatile static HeroAircraft instance = null;

    /**
     * @param locationX 英雄机位置x坐标
     * @param locationY 英雄机位置y坐标
     * @param speedX 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param speedY 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param hp    初始生命值
     */
    private HeroAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        power = 30;
        direction = -1;
        shootStrategy=new HeroShootStrategyL1();
    }

    /** 使用双重检查锁定DCL的单例模式*/
    public static HeroAircraft getInstance() {
        if (instance==null){
            synchronized (HeroAircraft.class){
                if (instance==null) {
                    instance = new HeroAircraft(
                            Main.WINDOW_WIDTH / 2,
                            Main.WINDOW_HEIGHT - ImageManager.get(HeroAircraft.class).getHeight() ,
                            0, 0, 200);
                }
            }
        }
        return instance;
    }

    @Override
    public void forward() {
        // 英雄机由鼠标控制，不通过forward函数移动
    }

    public void resetLocation(){
        setLocation(Main.WINDOW_WIDTH / 2,Main.WINDOW_HEIGHT - ImageManager.get(HeroAircraft.class).getHeight());
    }

}
