package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class LifePropTest {
    LifeProp lifeProp;

    @BeforeAll
    static void beforeAll() {
        System.out.println("**------LifePropTest Begin------**");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("**------LifePropTest End------**");
    }

    @BeforeEach
    void setUp(TestInfo testInfo) {
        System.out.print(testInfo.getDisplayName()+"...");
        lifeProp=new LifeProp(0,0);
    }

    @AfterEach
    void tearDown() {
        lifeProp=null;
        System.out.println("End");
    }

    @DisplayName("LifeProp Crash Test")
    @Test
    void crash() {
        HeroAircraft.getInstance().setLocation(0,0);
        assertTrue(lifeProp.crash(HeroAircraft.getInstance()));
        HeroAircraft.getInstance().setLocation(100,100);
        assertFalse(lifeProp.crash(HeroAircraft.getInstance()));
    }

    @DisplayName("LifeProp Work Test")
    @Test
    void work() {
        HeroAircraft.getInstance().setHp(10);
        lifeProp.work(HeroAircraft.getInstance());
        assertEquals(HeroAircraft.getInstance().getHp(),60);
        HeroAircraft.getInstance().setHp(90);
        lifeProp.work(HeroAircraft.getInstance());
        assertEquals(HeroAircraft.getInstance().getHp(),100);
    }
}