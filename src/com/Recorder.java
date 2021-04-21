package com;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Recorder {
    private float sampleRate;
    private int sampleSizeInBits;
    private int channels;
    private boolean signed;
    private boolean bigEndian;

    public Recorder(float sampleaRate, int sampleSizeInBits, int channels, boolean signed, boolean bigEndian) {
        this.sampleRate = sampleaRate;
        this.sampleSizeInBits = sampleSizeInBits;
        this.channels = channels;
        this.signed = signed;
        this.bigEndian = bigEndian;
    }

    private AudioFormat audioFormat;
    private TargetDataLine targetDataLine;

    public void captureAudio() {
        try {
            audioFormat = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
            DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
            if(!AudioSystem.isLineSupported(dataLineInfo)) {
                System.out.println("Line not supported");
                System.exit(0);
            }
            targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
            thread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
            File audioFile = new File("recording.wav");
            try {
                targetDataLine.open(audioFormat);
                targetDataLine.start();
                AudioSystem.write(new AudioInputStream(targetDataLine), fileType, audioFile);
            } catch (LineUnavailableException | IOException e) {
                e.printStackTrace();
            }
        }
    });

    public void stopAudio() {
        targetDataLine.stop();
        targetDataLine.close();
    }
}
