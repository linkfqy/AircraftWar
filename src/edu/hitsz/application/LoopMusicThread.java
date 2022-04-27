package edu.hitsz.application;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 循环播放音乐，可随时停止
 * @author linkfqy
 */
public class LoopMusicThread extends MusicThread {

    public LoopMusicThread(String filename) {
        super(filename);
    }

    @Override
    public void run() {
        stop=false;
        InputStream stream = new ByteArrayInputStream(samples);
        try {
            while (!stop) {
                stream.reset();
                play(stream);
            }
        } catch (InterruptedException e) {
            // 解除停止标记
            stop=false;
        } catch (IOException e) {
            e.printStackTrace();
       }
    }
    public static void main(String[] args) throws InterruptedException {
        LoopMusicThread mt=new LoopMusicThread("src/audios/bgm.wav");
        mt.start();
    }
}