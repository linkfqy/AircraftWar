package edu.hitsz.application;

import edu.hitsz.aircraft.*;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.dao.Record;
import edu.hitsz.dao.RecordDao;
import edu.hitsz.dao.RecordDaoImpl;
import edu.hitsz.factory.*;
import edu.hitsz.gui.RankingPanel;
import edu.hitsz.prop.AbstractProp;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;

/**
 * 游戏主面板，游戏启动
 *
 * @author hitsz
 */
public class Game extends JPanel {

    private int backGroundTop = 0;

    /**
     * Scheduled 线程池，用于任务调度
     */
    private final ScheduledExecutorService executorService;

    /**
     * 时间间隔(ms)，控制刷新频率
     */
    private final int timeInterval = 40;

    private final HeroAircraft heroAircraft;
    private final List<AbstractEnemy> enemyAircrafts;
    private final List<BaseBullet> heroBullets;
    private final List<BaseBullet> enemyBullets;
    private final List<AbstractProp> props;

    private int enemyMaxNumber = 5;

    private boolean gameOverFlag = false;
    private int score = 0;
    /**
     * Boss机最后一次被打爆时的分数，score-lastScore>=bossScoreThreshold，且无Boss机时产生新的Boss机
     */
    private int lastScore = 0;
    /**
     * 场上是否还有Boss的存活标志
     */
    private boolean bossAlive = false;
    private int bossScoreThreshold =300;
    private int time = 0;
    /**
     * 敌机生成、敌机射击、本机射击的周期(ms)
     */
    private CycleManager enemyGenCycle=new CycleManager(600);
    private CycleManager enemyShootCycle=new CycleManager(600);
    private CycleManager heroShootCycle= new CycleManager(150);

    /**
     * 游戏记录Dao，用于实现排行榜功能
     */
    private RecordDao recordDao;
    private static final String RECORD_DAO_PATH="./Records.dat";

    /**
     * 从文件读取recordDao
     */
    private void readRecordDao(){
        try {
            ObjectInputStream ois=new ObjectInputStream(new FileInputStream(RECORD_DAO_PATH));
            recordDao=(RecordDao) ois.readObject();
        }catch (FileNotFoundException e){
            recordDao=new RecordDaoImpl();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 向文件写入recordDao
     */
    private void writeRecordDao(){
        try {
            ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(RECORD_DAO_PATH));
            oos.writeObject(recordDao);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 控制台输出排行榜
     */
    private void addAndPrintRanking(){
        recordDao.add("testUserName",score,new Date());
        System.out.println("*".repeat(20)+"排行榜"+"*".repeat(20));
        List<Record> ranking=recordDao.getSorted();
        for (int i=0;i<ranking.size();i++){
            Record r=ranking.get(i);
            System.out.println("第"+(i+1)+"名："+r.getName()+", "+r.getScore()+", "+r.getDate());
        }
    }

    /**
     * 敌机工厂列表，依概率生成敌机
     */
    private final List<AbstractEnemyFactory> enemyFactories = List.of(
            new MobEnemyFactory(),
            new EliteEnemyFactory()
    );
    /**
     * 敌机概率列表，表示各敌机生成的概率（以100为单位）
     */
    private final List<Integer> enemyProb = List.of(70,30);
    private final BossEnemyFactory bossEnemyFactory = new BossEnemyFactory();

    /**
     * 道具工厂列表，依概率生成道具
     */
    private final List<AbstractPropFactory> propFactories = List.of(
            new LifePropFactory(),
            new BombPropFactory(),
            new FirePropFactory()
    );
    /**
     * 道具概率列表，表示各道具生成的概率（以100为单位）
     */
    private final List<Integer> propProb = List.of(30,30,30);

    /**
     * 随机选择器
     * @param pList 概率列表（以100为单位），代表每个下标被选择的概率
     * @return 选择的下标
     */
    private int selectRandomly(List<Integer> pList) {
        int p=Main.RAND.nextInt(100);
        int sum = 0;
        for (int i=0;i<pList.size();i++) {
            sum=sum+pList.get(i);
            if (p<=sum) {
                return i;
            }
        }
        // 默认“什么都不选”
        return -1;
    }

    public Game() {
        heroAircraft = HeroAircraft.getInstance();

        enemyAircrafts = new LinkedList<>();
        heroBullets = new LinkedList<>();
        enemyBullets = new LinkedList<>();
        props = new LinkedList<>();
        readRecordDao();


        ThreadFactory gameThread = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setName("Game thread");
                return t;
            }
        };
        //Scheduled 线程池，用于定时任务调度
        executorService = new ScheduledThreadPoolExecutor(1, gameThread);

        //启动英雄机鼠标监听
        new HeroController(this, heroAircraft);

    }

    /**
     * 游戏启动入口，执行游戏逻辑
     */
    public void action() {

        // 定时任务：绘制、对象产生、碰撞判定、击毁及结束判定
        Runnable task = () -> {

            time += timeInterval;

            // 周期性生成敌机
            generateEnemyAction();

            // 敌机周期性射击
            enemyShootAction();

            // 英雄机周期性射击
            heroShootAction();

            // 子弹移动
            bulletMoveAction();

            // 飞机移动
            aircraftMoveAction();

            // 道具移动
            propMoveAction();

            // 撞击检测
            crashCheckAction();

            // 后处理
            postProcessAction();

            //每个时刻重绘界面
            repaint();

            //调试输出
            debug();

            // 游戏结束检查
            if (heroAircraft.getHp() <= 0) {
                // 游戏结束
                executorService.shutdown();
                gameOverFlag = true;
                System.out.println("Game Over!");

                RankingPanel rankingPanel=new RankingPanel();
                Container parent=(Container)SwingUtilities.getRoot(this);
                parent.remove(this);
                parent.add(rankingPanel.getMainPanel());
                parent.setVisible(true);

                addAndPrintRanking();
                writeRecordDao();
            }

        };

        /*以固定延迟时间进行执行
         *本次任务执行完成后，需要延迟设定的延迟时间，才会执行新的任务*/
        executorService.scheduleWithFixedDelay(task, timeInterval, timeInterval, TimeUnit.MILLISECONDS);

    }

    //***********************
    //      Action 各部分
    //***********************

    private void generateEnemyAction() {
        if (enemyGenCycle.isNewCycle(time)) {
            // 新敌机产生
            if (enemyAircrafts.size() < enemyMaxNumber) {
                // 积累一定分数，且场上无Boss机时产生新的Boss机
                if (score-lastScore>= bossScoreThreshold && !bossAlive){
                    enemyAircrafts.add(bossEnemyFactory.create());
                }
                //依概率随机产生敌机
                else {
                    enemyAircrafts.add(enemyFactories.get(selectRandomly(enemyProb)).create());
                }
            }
        }
    }

    private void enemyShootAction() {
        if (enemyShootCycle.isNewCycle(time)){
            for (AbstractEnemy enemyAircraft : enemyAircrafts){
                enemyBullets.addAll(enemyAircraft.shoot());
            }
        }
    }

    private void heroShootAction() {
        if (heroShootCycle.isNewCycle(time)){
            heroBullets.addAll(heroAircraft.shoot());
        }
    }

    private void bulletMoveAction() {
        for (BaseBullet bullet : heroBullets) {
            bullet.forward();
        }
        for (BaseBullet bullet : enemyBullets) {
            bullet.forward();
        }
    }

    private void aircraftMoveAction() {
        for (AbstractEnemy enemyAircraft : enemyAircrafts) {
            enemyAircraft.forward();
        }
    }

    private void propMoveAction() {
        for (AbstractProp prop : props){
            prop.forward();
        }
    }


    /**
     * 碰撞检测：
     * 1. 敌机攻击英雄
     * 2. 英雄攻击/撞击敌机
     * 3. 英雄获得补给
     */
    private void crashCheckAction() {
        // 敌机子弹攻击英雄
        for (BaseBullet bullet : enemyBullets) {
            if (bullet.notValid()) {
                continue;
            }
            if (heroAircraft.crash(bullet)) {
                heroAircraft.decreaseHp(bullet.getPower());
                bullet.vanish();
            }
        }

        // 敌机撞英雄子弹或英雄机
        for (AbstractEnemy enemyAircraft : enemyAircrafts) {
            if (enemyAircraft.notValid()) {
                continue;  // 已被其他子弹击毁的敌机，不再检测，避免多个子弹重复击毁同一敌机的判定
            }
            for (BaseBullet bullet : heroBullets) {
                if (bullet.notValid()) {
                    continue;
                }
                if (enemyAircraft.crash(bullet)) {
                    // 敌机撞击到英雄机子弹
                    // 敌机损失一定生命值
                    enemyAircraft.decreaseHp(bullet.getPower());
                    bullet.vanish();
                    if (enemyAircraft.notValid()) {
                        score += enemyAircraft.getScore();
                        //打败非普通敌机产生道具补给
                        if (enemyAircraft instanceof MobEnemy) {
                            continue;
                        }
                        try {
                            props.add(propFactories.get(selectRandomly(propProb)).create(enemyAircraft));
                        }catch (IndexOutOfBoundsException ignored){}
                        break;
                    }
                }
            }
            // 敌机撞英雄机，均损毁
            if (enemyAircraft.crash(heroAircraft)) {
                enemyAircraft.vanish();
                heroAircraft.decreaseHp(Integer.MAX_VALUE);
            }
        }

        // 我方获得道具，道具生效
        for (AbstractProp prop : props){
            if (heroAircraft.crash(prop)){
                prop.work(heroAircraft);
            }
        }
    }

    /**
     * 后处理：
     * 1. 删除无效的子弹
     * 2. 删除无效的敌机
     * 3. 删除无效道具
     * 4. 检查英雄机生存
     * 5. 检查Boss机生存
     * <p>
     * 无效的原因可能是撞击或者飞出边界
     */
    private void postProcessAction() {
        enemyBullets.removeIf(AbstractFlyingObject::notValid);
        heroBullets.removeIf(AbstractFlyingObject::notValid);
        enemyAircrafts.removeIf(AbstractFlyingObject::notValid);
        props.removeIf(AbstractFlyingObject::notValid);

        boolean newBossAlive = false;
        for (AbstractEnemy enemy:enemyAircrafts){
            if (enemy instanceof BossEnemy){
                newBossAlive = true;
                break;
            }
        }
        if (bossAlive && !newBossAlive){
            lastScore=score;
        }
        bossAlive=newBossAlive;
    }

    private void debug(){
//        System.out.println("BulletNum:"+heroBullets.size());
    }


    //***********************
    //      Paint 各部分
    //***********************

    /**
     * 重写paint方法
     * 通过重复调用paint方法，实现游戏动画
     *
     * @param  g Graphics
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // 绘制背景,图片滚动
        g.drawImage(ImageManager.BACKGROUND_IMAGE, 0, this.backGroundTop - Main.WINDOW_HEIGHT, null);
        g.drawImage(ImageManager.BACKGROUND_IMAGE, 0, this.backGroundTop, null);
        this.backGroundTop += 1;
        if (this.backGroundTop == Main.WINDOW_HEIGHT) {
            this.backGroundTop = 0;
        }

        // 1.绘制子弹，2.绘制道具，3.绘制飞机
        // 实现图层的效果
        paintImageWithPositionRevised(g, enemyBullets);
        paintImageWithPositionRevised(g, heroBullets);
        paintImageWithPositionRevised(g, props);
        paintImageWithPositionRevised(g, enemyAircrafts);

        g.drawImage(ImageManager.get(HeroAircraft.class), heroAircraft.getLocationX() - heroAircraft.getImage().getWidth() / 2,
                heroAircraft.getLocationY() - heroAircraft.getImage().getHeight() / 2, null);

        //绘制得分和生命值
        paintScoreAndLife(g);

    }

    private void paintImageWithPositionRevised(Graphics g, List<? extends AbstractFlyingObject> objects) {
        if (objects.size() == 0) {
            return;
        }

        for (AbstractFlyingObject object : objects) {
            BufferedImage image = object.getImage();
            assert image != null : objects.getClass().getName() + " has no image! ";
            g.drawImage(image, object.getLocationX() - image.getWidth() / 2,
                    object.getLocationY() - image.getHeight() / 2, null);
        }
    }

    private void paintScoreAndLife(Graphics g) {
        int x = 10;
        int y = 25;
        g.setColor(new Color(16711680));
        g.setFont(new Font("SansSerif", Font.BOLD, 22));
        g.drawString("SCORE:" + this.score, x, y);
        y = y + 20;
        g.drawString("LIFE:" + this.heroAircraft.getHp(), x, y);
    }


}
