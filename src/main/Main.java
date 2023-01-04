package main;

import data.*;
import manager.*;
import gui.*;

// UI / GUI
import de.javasoft.synthetica.dark.SyntheticaDarkLookAndFeel;
import javax.swing.UIManager;

import java.util.ArrayList;

public class Main {
    private final Player player;
    private final MainWindow gui;
    private final PlaylistsManager playlistsManager;
    private final SongManager songManager;

    public static void main(String[] args) {
        //HTTPS_Helper httpsHelper = new HTTPS_Helper("https://192.168.188.67", "/files/","/upload/upload-java.php");
        //httpsHelper.postSong(System.getProperty("user.dir")+"/audio/test.wav");
        //httpsHelper.postSong("C:/Users/Linus/Downloads/imager_1.7.3.exe");
        new Main();
    }

    public Main() {
        setUIManager();
        this.player = new Player();
        this.playlistsManager = new PlaylistsManager();
        this.gui = new MainWindow("Music-Player", this);
        this.songManager = new SongManager("hswsql.ddns.net");
        startMainLoop();
        // DEBUG OPEN
        player.openSong(System.getProperty("user.dir")+"/audio/piano2.wav");
    }

    public void setUIManager(){
        try{
            UIManager.setLookAndFeel(new SyntheticaDarkLookAndFeel());
        } catch(Exception e){
            System.out.println("Error while importing UI Manager:");
            e.printStackTrace();
        }
    }

    public void playMusic(){
        player.startSong();
    }

    public void pauseMusic(){
        player.pauseSong();
    }
    public void skipMusic(){
        Playlist currentPlaylist = playlistsManager.getActivePlaylist();
        Song nextSong = playlistsManager.nextSong();
        if(nextSong == null){
            stopSong();
            return;
        };
        gui.setSongInfo(nextSong);
        gui.updatePlaylist(currentPlaylist);
        player.openSong(nextSong.filename());

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

    public void searchSong(String searchString){
        ArrayList<Song> result = songManager.search(searchString);
    }



}