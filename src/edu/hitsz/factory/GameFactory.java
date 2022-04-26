package edu.hitsz.factory;

import edu.hitsz.application.Game;

/**
 * Game工厂，创建不同难度的Game
 * @author linkfqy
 */
public class GameFactory {
    public static Game newGame(){
        return new Game();
    }
    public static Game newEasyGame(){
        Game game=newGame();
        return game;
    }
    public static Game newNormalGame(){
        Game game=newGame();
        return game;
    }
    public static Game newHardGame(){
        Game game=newGame();
        return game;
    }
}
