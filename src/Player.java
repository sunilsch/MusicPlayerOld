import javax.sound.sampled.*;
import java.io.File;

public class Player {
    private final Clip clip;


    public Player() throws LineUnavailableException {
        clip = AudioSystem.getClip();
    }

    public void openSong(String filename){
        System.out.println("Play song!");
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
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stopSong(){
        System.out.println("Stop song!");
        clip.stop();
    }

    public long getCurrentTimePosition(){
        return clip.getMicrosecondPosition();
    }

    public long getTotalTime(){
        return clip.getMicrosecondLength();
    }
}
