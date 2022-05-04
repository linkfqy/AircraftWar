package edu.hitsz.application;

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
    protected void play(InputStream stream)throws InterruptedException{
        try {
            while (!stop) {
                stream.reset();
                playOnce(stream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}