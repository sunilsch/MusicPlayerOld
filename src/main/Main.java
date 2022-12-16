package main;

import de.javasoft.synthetica.dark.SyntheticaDarkLookAndFeel;

import javax.swing.*;
import java.text.ParseException;

import gui.MainWindow;
import data.PlaylistsManagement;

public class Main {

    private final Player player;
    private final MainWindow gui;
    private final PlaylistsManagement playlistsManagement;



    public static void main(String[] args) throws UnsupportedLookAndFeelException, ParseException {
        new Main();
    }

    public Main() throws ParseException, UnsupportedLookAndFeelException {
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
    }
    public void reverseSkipMusic(){
    }

    /*
     * _______________________ *
     *
     *  PROGRESS BAR
     *
     * _______________________ *
     *
     */

    public void startProgressBarProcess(){
        new Thread(this::updateProgressBar).start();
    }
    private void updateProgressBar() {
        while (true) {

            gui.updateProgress(player.getCurrentTimePosition(), player.getTotalTime());
            System.out.print(".");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}