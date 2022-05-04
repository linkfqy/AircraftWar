package edu.hitsz.aircraft.factory;

import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

/**
 * @author linkfqy
 */
public class BossEnemyFactory extends AbstractEnemyFactory {
    @Override
    public BossEnemy create() {
        return new BossEnemy(
                (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.get(BossEnemy.class).getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2),
                baseSpeed /2,0, getHp()
        );
    }

    @Override
    public int getHp() {
        return (int)(baseHp*12);
    }
}
