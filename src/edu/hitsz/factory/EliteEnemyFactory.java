package edu.hitsz.factory;

import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

/**
 * @author linkfqy
 */
public class EliteEnemyFactory extends AbstractEnemyFactory {
    @Override
    public EliteEnemy create() {
        return new EliteEnemy(
                (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.get(EliteEnemy.class).getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2),
                (Main.RAND.nextInt(2)*2-1)* baseSpeed /2, baseSpeed, getHp()
        );
    }

    @Override
    public int getHp() {
        return (int)(baseHp*5);
    }
}
