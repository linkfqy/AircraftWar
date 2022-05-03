package edu.hitsz.application;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.factory.AbstractEnemyFactory;

import java.util.List;

/**
 * @author linkfqy
 */
public class EasyGame extends BaseGame {
    public EasyGame(){
        super();
        // 设定背景图片
        backgroundImage = ImageManager.BACKGROUND_IMAGE_EASY;
    }

    @Override
    protected void difficultyInitialization() {
        // 不产生Boss
        bossScoreThreshold = Integer.MAX_VALUE;
        mobEnemyFactory.setBaseHp(45);
        eliteEnemyFactory.setBaseHp(45);
        AbstractEnemyFactory.setBaseSpeed(5);
        enemyProb = List.of(70,30);
        enemyGenCycle.setCycleDuration(600);
        enemyMaxNumber=5;

        HeroAircraft.getInstance().setMaxHp(200);
        HeroAircraft.getInstance().setHp(200);
        printInfo("Initial Info");
    }

    /**
     * 简单模式难度固定
     */
    @Override
    protected void difficultyUpdate() {}
}
