package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import org.junit.jupiter.api.*;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EliteEnemyTest {
    private EliteEnemy eliteEnemy;

    @BeforeAll
    static void beforeAll() {
        System.out.println("**------EliteEnemyTest Begin------**");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("**------EliteEnemyTest End------**");
    }

    @BeforeEach
    void setUp(TestInfo testInfo) {
        System.out.print(testInfo.getDisplayName()+"...");
        eliteEnemy = new EliteEnemy(0,0,0,10);
    }

    @AfterEach
    void tearDown() {
        eliteEnemy=null;
        System.out.println("End");
    }

    @DisplayName("EliteEnemy Forward Test")
    @Test
    void forward() {
        int locationX=eliteEnemy.getLocationX();
        int locationY=eliteEnemy.getLocationY();
        int speedX=eliteEnemy.getSpeedX();
        int speedY=eliteEnemy.getSpeedY();
        eliteEnemy.forward();
        assertEquals(locationX+speedX,eliteEnemy.getLocationX());
        assertEquals(locationY+speedY,eliteEnemy.getLocationY());
    }

    @DisplayName("EliteEnemy Shoot Test")
    @Test
    void shoot() {
        List<BaseBullet> enemyBullets = new LinkedList<>();
        for (int i=0;i<10;i++){
            enemyBullets.addAll(eliteEnemy.shoot());
        }
        assertEquals(enemyBullets.size(),10);
    }
}