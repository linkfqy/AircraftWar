package edu.hitsz.application;

import edu.hitsz.factory.GameFactory;
import edu.hitsz.gui.RankingPanel;
import edu.hitsz.gui.StartPanel;

import javax.swing.*;

/**
 * 界面管理器
 * @author linkfqy
 */
public class PanelManager {
    private final JFrame frame;
    private StartPanel startPanel;
    private RankingPanel rankingPanel;
    private Game game;

    public enum Status{
        /**
         * 开始界面，简单模式，普通模式，困难模式，排行榜界面
         */
        START,EASY_GAME,NORMAL_GAME,HARD_GAME,RANKING
    }
    Status status;

    public PanelManager(JFrame frame){
        this.frame=frame;
        setStatus(Status.START);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        if (this.status == status){
            return;
        }
        JPanel newPanel=null;
        switch (status){
            case START:
                startPanel=new StartPanel();
                newPanel=startPanel.getMainPanel();
                break;
            case EASY_GAME:
                game = GameFactory.newEasyGame();
                newPanel=game;
                game.action();
                break;
            case NORMAL_GAME:
                game = GameFactory.newNormalGame();
                newPanel=game;
                game.action();
                break;
            case HARD_GAME:
                game = GameFactory.newHardGame();
                newPanel=game;
                game.action();
                break;
            case RANKING:
                rankingPanel=new RankingPanel();
                newPanel=rankingPanel.getMainPanel();
                break;
            default:
                break;
        }
        frame.getContentPane().removeAll();
        frame.add(newPanel);
        frame.setVisible(true);
        this.status=status;
    }
}
