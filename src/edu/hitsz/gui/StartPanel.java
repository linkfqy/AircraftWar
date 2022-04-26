package edu.hitsz.gui;

import edu.hitsz.application.Game;
import edu.hitsz.application.Main;
import edu.hitsz.application.PanelManager;
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
                Main.panelManager.setStatus(PanelManager.Status.EASY_GAME);
            }
        });
        //普通模式按钮的监听器
        normalModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.panelManager.setStatus(PanelManager.Status.NORMAL_GAME);
            }
        });
        //困难模式按钮的监听器
        hardModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.panelManager.setStatus(PanelManager.Status.HARD_GAME);
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
