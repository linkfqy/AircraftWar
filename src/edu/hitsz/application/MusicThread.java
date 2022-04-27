package edu.hitsz.application;

import javax.sound.sampled.*;
import java.io.*;

/**
 * 单次播放音乐，可随时停止
 * @author linkfqy
 */
public class MusicThread extends Thread {


    /** 音频文件名 */
    private final String filename;
    private AudioFormat audioFormat;
    protected byte[] samples;

    protected boolean stop;
    public boolean isStop() {
        return stop;
    }
    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public MusicThread(String filename) {
        //初始化filename
        this.filename = filename;
        this.stop=false;
        reverseMusic();
    }

    public void reverseMusic() {
        try {
            //定义一个AudioInputStream用于接收输入的音频数据，使用AudioSystem来获取音频的音频输入流
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File(filename));
            //用AudioFormat来获取AudioInputStream的格式
            audioFormat = stream.getFormat();
            samples = getSamples(stream);
        } catch (UnsupportedAudioFileException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public byte[] getSamples(AudioInputStream stream) {
        int size = (int) (stream.getFrameLength() * audioFormat.getFrameSize());
        byte[] samples = new byte[size];
        DataInputStream dataInputStream = new DataInputStream(stream);
        try {
            dataInputStream.readFully(samples);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return samples;
    }

    public void play(InputStream source) throws InterruptedException{
        int size = (int) (audioFormat.getFrameSize() * audioFormat.getSampleRate());
//        int size = audioFormat.getFrameSize();
        byte[] buffer = new byte[size];
        //源数据行SourceDataLine是可以写入数据的数据行
        SourceDataLine dataLine = null;
        //获取受数据行支持的音频格式DataLine.info
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
        try {
            dataLine = (SourceDataLine) AudioSystem.getLine(info);
            dataLine.open(audioFormat, size);
        } catch (LineUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        assert dataLine != null;
        dataLine.start();
        try {
            int numBytesRead = source.read(buffer, 0, buffer.length);
            while (numBytesRead != -1 ) {
                //通过此源数据行将数据写入混频器
                dataLine.write(buffer, 0, numBytesRead);
				//从音频流读取指定的最大数量的数据字节，并将其放入缓冲区中
                numBytesRead = source.read(buffer, 0, buffer.length);
                if (stop) {
                    throw new InterruptedException();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        dataLine.drain();
        dataLine.close();
    }

    @Override
    public void run() {
        stop=false;
        InputStream stream = new ByteArrayInputStream(samples);
        try {
            play(stream);
        } catch (InterruptedException e) {
            // 解除停止标记
            stop=false;
        }
    }
    public static void main(String[] args) throws InterruptedException {
        MusicThread mt=new MusicThread("src/audios/bgm.wav");
        mt.start();
    }
}