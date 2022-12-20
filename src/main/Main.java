package main;

import data.Song;
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
        //HTTPS_Helper httpsHelper = new HTTPS_Helper("https://192.168.188.67", "/files/","/upload/upload-java.php");
        //httpsHelper.postSong(System.getProperty("user.dir")+"/audio/test.wav");
        //httpsHelper.postSong("C:/Users/Linus/Downloads/imager_1.7.3.exe");
        new Main();
    }

    public Main() throws ParseException, UnsupportedLookAndFeelException, LineUnavailableException {
        UIManager.setLookAndFeel(new SyntheticaDarkLookAndFeel());
        this.player = new Player();
        this.playlistsManagement = new PlaylistsManagement("192.168.188.67");
        this.gui = new MainWindow("Music-Player", this);
        startMainLoop();
        // DEBUG OPEN
        player.openSong(System.getProperty("user.dir")+"/audio/test.wav");
    }

    public void playMusic(){
        player.startSong();
    }

    public void pauseMusic(){
        player.pauseSong();
    }
    public void skipMusic(){
        Song nextSong = playlistsManagement.nextSong();
        if(nextSong == null){
            stopSong();
            return;
        };
        gui.setSong(nextSong);
        player.openSong(nextSong.getFilename());

    }
    public void reverseSkipMusic(){
        return;
    }
    public void startMainLoop(){
        new Thread(() -> {
            while(true){
                if(player.isOpen()){
                    gui.updateProgress(player.getCurrentTimePosition(), player.getTotalTime());
                    if(player.getCurrentTimePosition() == player.getTotalTime()){
                        System.out.println("Song finish playing next...");
                        skipMusic();
                    }
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public void stopSong(){
        player.closeSong();
        gui.closeMusic();
    }
}