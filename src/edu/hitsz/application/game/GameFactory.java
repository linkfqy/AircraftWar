package edu.hitsz.application.game;

import edu.hitsz.application.*;

/**
 * Game工厂，创建不同难度的Game
 * @author linkfqy
 */
public class GameFactory {
    public static BaseGame newGame(Main.GameMode gameMode){
        switch (gameMode){
            case EASY:
                return new EasyGame();
            case NORMAL:
                return new NormalGame();
            case HARD:
                return new HardGame();
            default:
                System.out.println("ERROR: UNEXPECTED GAME MODE");
                return new EasyGame();
        }
    }
}
