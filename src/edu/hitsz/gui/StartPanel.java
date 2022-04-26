package edu.hitsz.gui;

import edu.hitsz.application.Game;
import edu.hitsz.factory.GameFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author linkfqy
 */
public class StartPanel {
    private JPanel mainPanel;
    private JButton easyModeButton;
    private JButton normalModeButton;
    private JButton hardModeButton;
    private JComboBox<String> soundComboBox;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public StartPanel() {
        soundComboBox.addItem("开");
        soundComboBox.addItem("关");
        soundComboBox.setSelectedIndex(0);

        //简单模式按钮的监听器
        easyModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO 简单模式
                Container parent = (Container) SwingUtilities.getRoot(mainPanel);
                Game game= GameFactory.newEasyGame();
                parent.remove(mainPanel);
                parent.add(game);
                parent.setVisible(true);
                game.action();
            }
        });
        //普通模式按钮的监听器
        normalModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO 普通模式
                Container parent = (Container) SwingUtilities.getRoot(mainPanel);
                Game game= GameFactory.newNormalGame();
                parent.remove(mainPanel);
                parent.add(game);
                parent.setVisible(true);
                game.action();
            }
        });
        //困难模式按钮的监听器
        hardModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO 困难模式
                Container parent = (Container) SwingUtilities.getRoot(mainPanel);
                Game game= GameFactory.newHardGame();
                parent.remove(mainPanel);
                parent.add(game);
                parent.setVisible(true);
                game.action();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("startPanel");
        frame.setSize(512,768);
        frame.setResizable(false);
        frame.setBounds(0, 0,512,768);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        StartPanel sp=new StartPanel();
        frame.add(sp.getMainPanel());
        frame.setVisible(true);
    }
}
