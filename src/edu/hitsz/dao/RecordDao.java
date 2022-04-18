package edu.hitsz.dao;

import java.util.Date;
import java.util.List;

/**
 * 游戏记录的对象访问接口，表示整个排行榜
 * @author linkfqy
 */
public interface RecordDao {
    /**
     * 添加一条记录
     * @param name 用户名
     * @param score 分数
     * @param date 时间
     */
    void add(String name, int score, Date date);

    /**
     * 由id删除一条记录
     * @param recordId 待删除的记录编号
     */
    void deleteById(int recordId);

    /**
     * 获取按分数降序排名的整个排行榜
     * @return 已排名的排行榜
     */
    List<Record> getSorted();
}
