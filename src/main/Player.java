package main;

import javax.sound.sampled.*;
import java.io.File;

public class Player {
    private Clip clip;


    public Player() {
        try {
            clip = AudioSystem.getClip();
        } catch(Exception e){
            System.out.println("Error in Constructor of class Player");
            System.exit(1);
        }

    }
    public void closeSong(){
        if(clip.isOpen()){
            System.out.println("Close clip finish!");
            clip.close();

        }
    }


    public void openSong(String filename){
        closeSong();

        try{
            File file = new File(filename);
            AudioInputStream audio = AudioSystem.getAudioInputStream(file);
            clip.open(audio);
            System.out.println("Opened");
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void startSong(){
        System.out.println("Play song!");
        clip.start();
    }

    public void pauseSong(){
        System.out.println("Stop song!");
        clip.stop();
    }

    public long getCurrentTimePosition(){
        return clip.getMicrosecondPosition();
    }

    public long getTotalTime(){
        return clip.getMicrosecondLength();
    }

    public boolean isOpen(){
        return clip.isOpen();
    }
}
