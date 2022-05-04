package edu.hitsz.application;

import edu.hitsz.dao.RecordDao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;

/**
 * @author linkfqy
 */
public class RankingPanel {
    private JPanel mainPanel;
    private JLabel difficultyLabel;
    private JScrollPane tableScrollPane;
    private JTable rankingTable;
    private JButton deleteRecordButton;
    private JButton restartButton;
    private final RecordDao recordDao;
    DefaultTableModel model;
    String[] columnNames = {"名次","玩家名","得分","游戏时间"};
    int[] preferredColumnWidth = {55,157,68,192};

    public JPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * 根据预设的数值调整列宽
     */
    public void fitTableColumns() {
        TableColumnModel columnModel=rankingTable.getColumnModel();
        for (int i=0;i< preferredColumnWidth.length;i++){
            columnModel.getColumn(i).setPreferredWidth(preferredColumnWidth[i]);
        }
    }

    public void refreshTable(){
        String[][] tableData = recordDao.getSorted().toStringArray();
        model.setDataVector(tableData,columnNames);
        fitTableColumns();
    }

    public void deleteRow(int row){
        //从recordDao中删除记录
        recordDao.deleteById(recordDao.getByIndex(row).getId());
        //更新界面
        refreshTable();
        //写入文件
        recordDao.writeToFile(Main.getGameMode().getPath());
    }

    public RankingPanel (RecordDao recordDao) {
        //调整列标题字体字号
        rankingTable.getTableHeader().setFont(new Font("default", Font.PLAIN, 18));
        // 设置表格模型
        model=new DefaultTableModel(columnNames,0){
            @Override
            public boolean isCellEditable(int rol,int col){
                return false;
            }
        };
        rankingTable.setModel(model);
        // 绑定Scroll组件
        tableScrollPane.setViewportView(rankingTable);
        // 设置难度标签内容
        difficultyLabel.setText("难度："+Main.getGameMode());
        // 绑定recordDao
        this.recordDao=recordDao;
        refreshTable();
        //删除按钮的监听器
        deleteRecordButton.addActionListener(e -> {
            int row=rankingTable.getSelectedRow();
            if (row!=-1){
                int confirm = JOptionPane.showConfirmDialog(mainPanel,"确定删除选中的记录吗？","删除提示",
                        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (confirm==JOptionPane.YES_OPTION) {
                    deleteRow(row);
                }
            }
        });
        //重新开始按钮的监听器
        restartButton.addActionListener(e -> {
            mainPanel.setVisible(false);
            synchronized (Main.MAIN_LOCK){
                Main.MAIN_LOCK.notify();
            }
        });
    }
}
