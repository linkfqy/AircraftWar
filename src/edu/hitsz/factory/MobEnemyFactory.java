package edu.hitsz.factory;

import edu.hitsz.aircraft.MobEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

/**
 * @author linkfqy
 */
public class MobEnemyFactory extends AbstractEnemyFactory {
    @Override
    public MobEnemy create() {
        return new MobEnemy(
                (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.get(MobEnemy.class).getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2),
                0,BASE_SPEED,BASE_HP*2
        );
    }
}
