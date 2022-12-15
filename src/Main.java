import de.javasoft.synthetica.dark.SyntheticaDarkLookAndFeel;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.text.ParseException;

public class Main {
    private Player player;

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ParseException, LineUnavailableException {
        Main main = new Main();
    }

    public Main() throws ParseException, UnsupportedLookAndFeelException, LineUnavailableException {
        UIManager.setLookAndFeel(new SyntheticaDarkLookAndFeel());
        JFrame gui = new MainWindow("Music-Player", this);
        Player player = new Player();
        //player.playSong(System.getProperty("user.dir")+"/audio/test.wav");
    }


}