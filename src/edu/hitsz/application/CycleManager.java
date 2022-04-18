package edu.hitsz.application;

/**
 * 周期管理器
 * 管理敌机生成、敌机射击、本机射击等不同的周期
 * @author linkfqy
 */
public class CycleManager {
    private int cycleDuration;
    private int lastTime;

    public CycleManager(int cycleDuration){
        this.cycleDuration=cycleDuration;
        lastTime =0;
    }

    public int getCycleDuration() {
        return cycleDuration;
    }

    public void setCycleDuration(int cycleDuration) {
        this.cycleDuration = cycleDuration;
    }

    public boolean isNewCycle(int time){
        if (time- lastTime >=cycleDuration){
            lastTime =time;
            return true;
        }else{
            return false;
        }
    }
}
