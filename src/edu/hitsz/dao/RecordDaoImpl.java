package edu.hitsz.dao;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * RecordDao的一个实现
 * @author linkfqy
 */
public class RecordDaoImpl implements RecordDao,Serializable {
    private final List<Record> records;
    private int maxId;
    public RecordDaoImpl(){
        records=new ArrayList<>();
        maxId=0;
    }

    @Override
    public void add(String name, int score, Date date) {
        records.add(new Record(++maxId,name,score,date));
    }

    /**
     * 获取在List中序号（从0开始）为index的记录
     * @param index 待取记录的序号
     * @return 取得的记录，其序号是index
     */
    @Override
    public Record getByIndex(int index) {
        return records.get(index);
    }

    @Override
    public void deleteById(int recordId) {
        records.removeIf(r -> r.getId()==recordId);
    }

    @Override
    public RecordDao getSorted() {
        records.sort(Comparator.comparing(Record::getScore).reversed());
        return this;
    }

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    @Override
    public String[][] toStringArray() {
        String[][] result=new String[records.size()][4];
        for (int i=0;i<records.size();i++){
            result[i][0]=(i+1)+"";
            result[i][1]=records.get(i).getName();
            result[i][2]=records.get(i).getScore()+"";
            result[i][3]=sdf.format(records.get(i).getDate());
        }
        return result;
    }

    /**
     * 从文件读取RecordDaoImpl
     * @param path 待读取的文件路径
     * @return 读取到的RecordDaoImpl
     */
    public static RecordDaoImpl readFromFile(String path){
        RecordDaoImpl result = null;
        try {
            ObjectInputStream ois=new ObjectInputStream(new FileInputStream(path));
            result=(RecordDaoImpl) ois.readObject();
        }catch (FileNotFoundException|InvalidClassException e){
            result=new RecordDaoImpl();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 向文件写入RecordDaoImpl
     * @param path 待写入的文件路径
     */
    @Override
    public void writeToFile(String path){
        try {
            ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(path));
            oos.writeObject(this);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 将整个排行榜的内容打印到控制台
     */
    @Override
    public void printToConsole(){
        System.out.println("*".repeat(20)+"排行榜"+"*".repeat(20));
        String[][] ranking=this.getSorted().toStringArray();
        System.out.println(Arrays.toString(ranking));
    }
}
