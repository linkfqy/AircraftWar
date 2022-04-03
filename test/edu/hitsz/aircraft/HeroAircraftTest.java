package edu.hitsz.aircraft;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class HeroAircraftTest {

    @BeforeAll
    static void beforeAll() {
        System.out.println("**------HeroAircraftTest Begin------**");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("**------HeroAircraftTest End------**");
    }

    @BeforeEach
    void setUp(TestInfo testInfo) {
        System.out.print(testInfo.getDisplayName()+"...");
    }

    @AfterEach
    void tearDown() {
        System.out.println("End");
    }

    @DisplayName("HeroAircraft DecreaseHP Test")
    @Test
    void decreaseHp() {
        HeroAircraft.getInstance().setHp(100);
        HeroAircraft.getInstance().decreaseHp(10);
        assertEquals(HeroAircraft.getInstance().getHp(),90);
        HeroAircraft.getInstance().setHp(30);
        HeroAircraft.getInstance().decreaseHp(50);
        assertEquals(HeroAircraft.getInstance().getHp(),0);
    }

    @DisplayName("HeroAircraft GetInstance Test")
    @Test
    void getInstance() {
        HeroAircraft instance1=HeroAircraft.getInstance();
        HeroAircraft instance2=HeroAircraft.getInstance();
        assertEquals(instance1,instance2);
    }
}