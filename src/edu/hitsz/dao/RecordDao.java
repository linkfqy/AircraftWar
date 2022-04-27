package edu.hitsz.dao;

import java.io.Serializable;
import java.util.Date;

/**
 * 游戏记录的对象访问接口，表示整个排行榜
 * @author linkfqy
 */
public interface RecordDao extends Serializable {
    /**
     * 添加一条记录
     * @param name 用户名
     * @param score 分数
     * @param date 时间
     */
    void add(String name, int score, Date date);

    /**
     * 由数据结构中的序号获取一条记录
     * @param index 待取记录的序号
     * @return 取到的记录，它在数据结构中的序号是index
     */
    Record getByIndex(int index);

    /**
     * 由id删除一条记录
     * @param recordId 待删除的记录编号
     */
    void deleteById(int recordId);

    /**
     * 整个排行榜按分数降序排名，返回自身的一个引用，便于链式调用
     * @return 已排名的排行榜
     */
    RecordDao getSorted();

    /**
     * 将排行榜转化为二维字符串数组的形式
     * @return 二维字符串数组，每一行表示一条记录，列依次表示名次、玩家名、得分、游戏时间
     */
    String[][] toStringArray();

    /**
     * 向文件写入RecordDao
     * @param path 待写入的文件路径
     */
    void writeToFile(String path);

    /**
     * 将整个排行榜的内容打印到控制台
     */
    void printToConsole();
}
