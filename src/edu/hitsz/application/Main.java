package edu.hitsz.application;

import edu.hitsz.dao.RecordDao;
import edu.hitsz.dao.RecordDaoImpl;
import edu.hitsz.factory.GameFactory;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.Random;

/**
 * 程序入口
 * @author hitsz
 */
public class Main {

    public static final int WINDOW_WIDTH = 512;
    public static final int WINDOW_HEIGHT = 768;
    public enum GameMode{
        /**
         * 简单、普通、困难三种模式
         */
        EASY,NORMAL,HARD;
        public String getPath(){
            switch (this){
                case EASY:return "./EasyGameRecords.dat";
                case NORMAL:return "./NormalGameRecords.dat";
                default:return "./HardGameRecords.dat";
            }
        }
    }

    /**
     * 随机数生成器，用于控制生成敌机或道具等事件
     */
    public static final Random RAND =new Random();

    /**
     * Main线程锁
     */
    public static final Object MAIN_LOCK = new Object();

    private static JFrame frame;
    /**
     * 选择的游戏模式，由开始界面决定
     */
    private static GameMode gameMode;
    public static GameMode getGameMode() {
        return gameMode;
    }
    public static void setGameMode(GameMode gameMode) {
        Main.gameMode = gameMode;
    }

    /**
     * 等待某个界面关闭
     * 需要在关闭时以MAIN_LOCK唤醒Main线程
     * @param panel 等待的界面
     */
    private static void waitFor(JPanel panel){
        synchronized (MAIN_LOCK){
            //循环等待开始界面被关闭
            while (panel.isVisible()){
                try {
                    MAIN_LOCK.wait();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
        frame.getContentPane().removeAll();
    }

    public static void main(String[] args) {
        System.out.println("Hello Aircraft War");

        // 获得屏幕的分辨率，初始化 Frame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame = new JFrame("Aircraft War");
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setResizable(false);
        //设置窗口的大小和位置,居中放置
        frame.setBounds(((int) screenSize.getWidth() - WINDOW_WIDTH) / 2, 0,
                WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //使用线程锁实现不同界面的切换
        //开始界面
        StartPanel startPanel = new StartPanel();
        frame.add(startPanel.getMainPanel());
        frame.setVisible(true);
        waitFor(startPanel.getMainPanel());
        //游戏界面
        BaseGame game = GameFactory.newGame(gameMode);
        frame.add(game);
        frame.setVisible(true);
        MusicThread.setMuted(startPanel.isMuted());
        game.action();
        waitFor(game);

        //排行榜数据
        RecordDao recordDao= RecordDaoImpl.readFromFile(gameMode.getPath());
        //排行榜界面
        RankingPanel rankingPanel = new RankingPanel(recordDao);
        frame.add(rankingPanel.getMainPanel());
        frame.setVisible(true);
        String name=JOptionPane.showInputDialog(rankingPanel.getMainPanel(),
                "太棒了，你在本次游戏中获得了"+game.getScore()+"分！\n"+"在此留下你的大名吧",
                "请输入名字", JOptionPane.QUESTION_MESSAGE);
        if (name!=null) {
            recordDao.add(name, game.getScore(), new Date());
        }
        rankingPanel.refreshTable();
        recordDao.writeToFile(gameMode.getPath());
    }
}
