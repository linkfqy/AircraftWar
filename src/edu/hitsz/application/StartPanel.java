package edu.hitsz.application;

import javax.swing.*;

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

    public boolean isMuted(){
        return soundComboBox.getSelectedIndex() == 1;
    }

    public StartPanel() {
        soundComboBox.addItem("开");
        soundComboBox.addItem("关");
        soundComboBox.setSelectedIndex(0);

        //简单模式按钮的监听器
        easyModeButton.addActionListener(e -> {
            Main.setGameMode(Main.GameMode.EASY);
            mainPanel.setVisible(false);
            synchronized (Main.MAIN_LOCK){
                Main.MAIN_LOCK.notify();
            }
        });
        //普通模式按钮的监听器
        normalModeButton.addActionListener(e -> {
            Main.setGameMode(Main.GameMode.NORMAL);
            mainPanel.setVisible(false);
            synchronized (Main.MAIN_LOCK){
                Main.MAIN_LOCK.notify();
            }
        });
        //困难模式按钮的监听器
        hardModeButton.addActionListener(e -> {
            Main.setGameMode(Main.GameMode.HARD);
            mainPanel.setVisible(false);
            synchronized (Main.MAIN_LOCK){
                Main.MAIN_LOCK.notify();
            }
        });
    }
}
