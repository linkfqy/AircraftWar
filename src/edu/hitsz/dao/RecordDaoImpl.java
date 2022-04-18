package edu.hitsz.dao;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * RecordDao的一个实现
 * @author linkfqy
 */
public class RecordDaoImpl implements RecordDao,Serializable {
    List<Record> records;
    int maxId;
    public RecordDaoImpl(){
        records=new LinkedList<>();
        maxId=0;
    }

    @Override
    public void add(String name, int score, Date date) {
        records.add(new Record(++maxId,name,score,date));
    }

    @Override
    public void deleteById(int recordId) {
        records.removeIf(r -> r.getId()==recordId);
    }

    @Override
    public List<Record> getSorted() {
        records.sort(Comparator.comparing(Record::getScore).reversed());
        return records;
    }
}
