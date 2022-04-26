package edu.hitsz.application;

import edu.hitsz.factory.GameFactory;
import edu.hitsz.gui.RankingPanel;
import edu.hitsz.gui.StartPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * 程序入口
 * @author hitsz
 */
public class Main {

    public static final int WINDOW_WIDTH = 512;
    public static final int WINDOW_HEIGHT = 768;

    /**
     * 随机数生成器，用于控制生成敌机或道具等事件
     */
    public static final Random RAND =new Random();

    public static PanelManager panelManager;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Aircraft War");

        System.out.println("Hello Aircraft War");

        // 获得屏幕的分辨率，初始化 Frame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setResizable(false);
        //设置窗口的大小和位置,居中放置
        frame.setBounds(((int) screenSize.getWidth() - WINDOW_WIDTH) / 2, 0,
                WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //使用界面管理器实现不同界面的切换
        panelManager=new PanelManager(frame);
    }
}
