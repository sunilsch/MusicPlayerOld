package main;

import data.PlaylistsManagement;
import de.javasoft.synthetica.dark.SyntheticaDarkLookAndFeel;
import gui.MainWindow;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.io.IOException;
import java.text.ParseException;

public class Main {
    private final Player player;
    private final MainWindow gui;
    private final PlaylistsManagement playlistsManagement;



    public static void main(String[] args) throws UnsupportedLookAndFeelException, ParseException, LineUnavailableException, IOException {
        // HTTPS_Helper.getFromHTTPS("https://192.168.188.67/files/LRMonoPhase4.wav", "/LRMonoPhase4.wav");
        new Main();
    }

    public Main() throws ParseException, UnsupportedLookAndFeelException, LineUnavailableException {
        UIManager.setLookAndFeel(new SyntheticaDarkLookAndFeel());
        this.player = new Player();
        this.playlistsManagement = new PlaylistsManagement();
        this.gui = new MainWindow("Music-Player", this);
        startProgressBarProcess();
        // DEBUG OPEN
        player.openSong(System.getProperty("user.dir")+"/audio/test.wav");
    }

    public void playMusic(){
        player.startSong();
    }

    public void stopMusic(){
        player.stopSong();
    }
    public void skipMusic(){
        return;
    }
    public void reverseSkipMusic(){
        return;
    }
    public void startProgressBarProcess(){
        new Thread(() -> {
            while(true){
                gui.updateProgress(player.getCurrentTimePosition(), player.getTotalTime());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}