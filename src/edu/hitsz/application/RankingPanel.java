package edu.hitsz.application;

import edu.hitsz.dao.RecordDao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

/**
 * @author linkfqy
 */
public class RankingPanel {
    private JPanel mainPanel;
    private JLabel difficultyLabel;
    private JScrollPane tableScrollPanel;
    private JTable rankingTable;
    private JButton deleteRecordButton;
    private final RecordDao recordDao;
    DefaultTableModel model;
    String[] columnNames = {"名次","玩家名","得分","游戏时间"};

    public JPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * 根据内容自动调整表格列宽
     * @param table 待调整的表格
     */
    public void fitTableColumns(JTable table) {
        JTableHeader header = table.getTableHeader();
        int rowCount = table.getRowCount();

        Enumeration<TableColumn> columns = table.getColumnModel().getColumns();
        while (columns.hasMoreElements()) {
            TableColumn column = columns.nextElement();
            int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
            int width = (int) table.getTableHeader().getDefaultRenderer()
                    .getTableCellRendererComponent(table, column.getIdentifier()
                            , false, false, -1, col).getPreferredSize().getWidth();
            for (int row = 0; row < rowCount; row++) {
                int preferredWidth = (int) table.getCellRenderer(row, col).getTableCellRendererComponent(table,
                        table.getValueAt(row, col), false, false, row, col).getPreferredSize().getWidth();
                width = Math.max(width, preferredWidth);
            }
            header.setResizingColumn(column);
            column.setWidth(width + table.getIntercellSpacing().width+10);
        }
    }

    public void refreshTable(){
        String[][] tableData = recordDao.getSorted().toStringArray();
        model.setDataVector(tableData,columnNames);
        fitTableColumns(rankingTable);
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
        rankingTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        model=new DefaultTableModel(columnNames,0){
            @Override
            public boolean isCellEditable(int rol,int col){
                return false;
            }
        };
        rankingTable.setModel(model);
        tableScrollPanel.setViewportView(rankingTable);
        difficultyLabel.setText("难度："+Main.getGameMode());
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
    }
}
