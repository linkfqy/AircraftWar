package edu.hitsz.factory;

import edu.hitsz.aircraft.MobEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class MobEnemyFactory extends EnemyFactory {
    @Override
    public MobEnemy create() {
        return new MobEnemy(
                (int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.get(MobEnemy.class).getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2),
                0,10
        );
    }
}
