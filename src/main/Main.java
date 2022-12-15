package main;

import de.javasoft.synthetica.dark.SyntheticaDarkLookAndFeel;
import gui.MainWindow;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.text.ParseException;

public class Main {
    private final Player player;

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ParseException, LineUnavailableException {
        Main main = new Main();
    }

    public Main() throws ParseException, UnsupportedLookAndFeelException, LineUnavailableException {
        UIManager.setLookAndFeel(new SyntheticaDarkLookAndFeel());
        JFrame gui = new MainWindow("Music-main.Player", this);
        this.player = new Player();

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
}