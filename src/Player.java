import javax.sound.sampled.*;
import java.io.File;

public class Player {
    private final Clip clip;


    public Player() throws LineUnavailableException {
        clip = AudioSystem.getClip();
    }

    public void openSong(String filename){
        System.out.print("Play song!");
        try{
            File file = new File(filename);
            AudioInputStream audio = AudioSystem.getAudioInputStream(file);
            clip.open(audio);
            System.out.print("Opened");
        } catch (Exception e){
            System.out.print(e);
        }
    }

    public void startSong(){
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stopSong(){
        System.out.print("Stop song!");
        clip.stop();
    }

    public long getCurrentTimePosition(){
        return clip.getMicrosecondPosition();
    }

    public long getTotalTime(){
        return clip.getMicrosecondLength();
    }
}
