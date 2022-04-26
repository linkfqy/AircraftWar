package edu.hitsz.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author linkfqy
 */
public class RankingPanel {
    private JPanel mainPanel;
    private JLabel difficultyLabel;
    private JScrollPane tableScrollPanel;
    private JTable rankingTable;
    private JButton deleteRecordButton;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public RankingPanel () {
        String[] columnName = {"学号","姓名","成绩"};
        String[][] tableData ={{"001","Lily","78"},{"002","Jane","89"},{"003","Alex","67"},
                               {"004","Macy","83"},{"005","Nancy","66"},{"006","John","99"},{"006","John","99"},{"006","John","99"},{"006","John","99"},{"006","John","99"},{"006","John","99"},{"006","John","99"},{"006","John","99"},{"006","John","99"},{"006","John","99"},{"006","John","99"},{"006","John","99"},{"006","John","99"},{"006","John","99"},{"006","John","99"},{"006","John","99"},{"006","John","99"},{"006","John","99"},{"006","John","99"},{"006","John","99"}};
        rankingTable.getTableHeader().setFont(new Font("default", Font.PLAIN, 18));
        DefaultTableModel model=new DefaultTableModel(tableData,columnName);
        rankingTable.setModel(model);
        tableScrollPanel.setViewportView(rankingTable);
        //删除按钮的监听器
        deleteRecordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row=rankingTable.getSelectedRow();
                if (row!=-1){
                    model.removeRow(row);
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("rankingPanel");
        frame.setSize(512,768);
        frame.setResizable(false);
        frame.setBounds(0, 0,512,768);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        StartPanel sp=new StartPanel();
        frame.add(new RankingPanel().mainPanel);
        frame.setVisible(true);
    }
}
