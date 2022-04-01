package edu.hitsz.application;


import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.aircraft.MobEnemy;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;
import edu.hitsz.prop.BombProp;
import edu.hitsz.prop.FireProp;
import edu.hitsz.prop.LifeProp;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

/**
 * 综合管理图片的加载，访问
 * 提供图片的静态访问方法
 *
 * @author hitsz
 */
public class ImageManager {

    /**
     * 类名-图片 映射，存储各基类的图片 <br>
     * 可使用 CLASSNAME_IMAGE_MAP.get( obj.getClass().getName() ) 获得 obj 所属基类对应的图片
     */
    private static final Map<String, BufferedImage> CLASSNAME_IMAGE_MAP = new HashMap<>();
    /**
     * 类-图片文件名 映射，由此生成CLASSNAME_IMAGE_MAP，避免手动映射的冗长写法
     */
    private static final Map<Class<?>, String> CLASS_FILENAME_MAP = Map.ofEntries(
            entry(HeroAircraft.class,"hero.png"),
            entry(MobEnemy.class,"mob.png"),
            entry(EliteEnemy.class,"elite.png"),
            entry(HeroBullet.class,"bullet_hero.png"),
            entry(EnemyBullet.class,"bullet_enemy.png"),
            entry(LifeProp.class,"prop_life.png"),
            entry(BombProp.class,"prop_bomb.png"),
            entry(FireProp.class,"prop_fire.png")
    );
    /**
     * 图片文件所在路径
     */
    private static final String IMG_PATH ="src/images/";

    public static BufferedImage BACKGROUND_IMAGE;

    static {
        try {

            BACKGROUND_IMAGE = ImageIO.read(new FileInputStream("src/images/bg.jpg"));

            for (Map.Entry<Class<?>,String> entry : CLASS_FILENAME_MAP.entrySet()){
                CLASSNAME_IMAGE_MAP.put(
                        entry.getKey().getName(),
                        ImageIO.read(new FileInputStream(IMG_PATH +entry.getValue()))
                );
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static BufferedImage get(String className){
        BufferedImage res = CLASSNAME_IMAGE_MAP.get(className);
        assert res!=null : className + "has no image!";
        return res;
    }

    public static BufferedImage get(Object obj){
        assert obj!=null : "ImageManager received a null get";
        return get(obj.getClass().getName());
    }

    public static BufferedImage get(Class<?> cls){
        assert cls!=null : "ImageManager received a null get";
        return get(cls.getName());
    }

}
