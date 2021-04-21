package com;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void waitOnChar() {
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            System.out.println("___MENU___");
            System.out.println("1. Record audio");
            System.out.println("2. Play recorded audio");
            System.out.println("3. Quit program");
            System.out.println("____________");
            System.out.println("Choose some option: ");

            Scanner sc = new Scanner(System.in);
            int option = sc.nextInt();

            switch (option) {
                case 1:
                    Recorder recorder = new Recorder(44100, 16, 2, true, false);
                    recorder.captureAudio();
                    System.out.println("Recording started. Press any key to stop recording.");
                    waitOnChar();
                    recorder.stopAudio();
                    System.out.println("Recording stopped.");
                    Thread.sleep(1000);
                    break;
                case 2:
                    System.out.println("Playing record started.");
                    PlayBack playBack = new PlayBack();
                    playBack.playRecord();
                    break;
                case 3:
                    System.exit(0);
            }
        }
    }
}
