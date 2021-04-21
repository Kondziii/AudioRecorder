package com;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class PlayBack {

    private Clip clip;
    public void playRecord()  {
        try {
            clip = AudioSystem.getClip();
            File record = new File("recording.wav");
            clip.open(AudioSystem.getAudioInputStream(record));
            clip.start();
            Thread.sleep(clip.getMicrosecondLength()/1000);
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
