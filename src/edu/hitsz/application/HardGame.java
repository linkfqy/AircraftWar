package edu.hitsz.application;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.factory.AbstractEnemyFactory;

import java.util.List;

import static java.lang.Math.*;

/**
 * @author linkfqy
 */
public class HardGame extends BaseGame{
    public HardGame(){
        super();
        // 设定背景图片
        backgroundImage=ImageManager.BACKGROUND_IMAGE_HARD;
    }

    private int t;
    @Override
    protected void difficultyInitialization() {
        t=0;
        bossEnemyFactory.setBaseHp(30);
        mobEnemyFactory.setBaseHp(30);
        eliteEnemyFactory.setBaseHp(30);
        AbstractEnemyFactory.setBaseSpeed(5);
        bossScoreThreshold=300;
        enemyProb= List.of(70,30);
        enemyGenCycle.setCycleDuration(600);
        enemyMaxNumber=5;

        HeroAircraft.getInstance().setMaxHp(700);
        HeroAircraft.getInstance().setHp(700);
        printInfo("Initial Info");
    }

    @Override
    protected void difficultyUpdate() {
        t++;
        bossEnemyFactory.setBaseHp(30+5*t);
        mobEnemyFactory.setBaseHp(30*sqrt(t/15.0)+30);
        eliteEnemyFactory.setBaseHp(30*sqrt(t/15.0)+30);
        AbstractEnemyFactory.setBaseSpeed(min((int)(5+0.5*t),10));
        bossScoreThreshold = max(300-15*t,150);
        int p=(int)(100.0*(30+2*t)/(100+2*t));
        enemyProb=List.of(100-p,p);
        enemyGenCycle.setCycleDuration(max(600-20*t,300));
        enemyMaxNumber=min((int)(5+0.5*t),8);

        printInfo(String.format("Difficulty Update-%d",t));
    }
}
