import javax.sound.sampled.*;
import java.io.File;

public class Player {
    private final Clip clip;


    public Player() throws LineUnavailableException {
        clip = AudioSystem.getClip();
    }

    public void playSong(String filename){
        try{
            File file = new File(filename);
            AudioInputStream audio = AudioSystem.getAudioInputStream(file);
            clip.open(audio);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            System.out.print("Started");
        } catch (Exception e){
            System.out.print(e);
        }
    }

    public void stopSong(){

    }

    public long getCurrentTimePosition(){
        return clip.getMicrosecondPosition();
    }

    public long getTotalTime(){
        return clip.getMicrosecondLength();
    }
}
