package edu.hitsz.application.game;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.aircraft.factory.AbstractEnemyFactory;
import edu.hitsz.application.ImageManager;
import edu.hitsz.prop.factory.AbstractPropFactory;

import java.util.List;

import static java.lang.Math.*;

/**
 * @author linkfqy
 */
public class NormalGame extends BaseGame{
    public NormalGame(){
        super();
        // 设定背景图片
        backgroundImage= ImageManager.BACKGROUND_IMAGE_NORMAL;
    }

    /** 难度增加的次数 */
    private int t;
    @Override
    protected void difficultyInitialization() {
        t=0;
        bossEnemyFactory.setBaseHp(60);
        mobEnemyFactory.setBaseHp(30);
        eliteEnemyFactory.setBaseHp(30);
        AbstractEnemyFactory.setBaseSpeed(5);
        bossScoreThreshold=300;
        enemyProb= List.of(70,30);
        enemyGenCycle.setCycleDuration(600);
        enemyMaxNumber=5;
        AbstractPropFactory.setTimeToVanish(10_000);

        HeroAircraft.getInstance().setMaxHp(500);
        HeroAircraft.getInstance().setHp(500);
        printInfo("Initial Info");
    }

    @Override
    protected void difficultyUpdate() {
        t++;
        mobEnemyFactory.setBaseHp(30*sqrt(t/20.0)+30);
        eliteEnemyFactory.setBaseHp(30*sqrt(t/20.0)+30);
        AbstractEnemyFactory.setBaseSpeed(min((int)(5+0.5*t),10));
        bossScoreThreshold = max(300-10*t,200);
        int p=(int)(100.0*(30+2*t)/(100+2*t));
        enemyProb=List.of(100-p,p);
        enemyGenCycle.setCycleDuration(max(600-10*t,300));
        enemyMaxNumber=min((int)(5+0.5*t),7);

        printInfo(String.format("Difficulty Update-%d",t));
    }
}
