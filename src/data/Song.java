package data;

/**
 * This class represents a song with name, artist and duration.
 * 
 * @author Linus
 * @version 15.12 v.2.0 Update
 *
 * Updated class to English
 */
public class Song
{
    private final String songName;
    private final String artist;
    private final double duration;
    private final String filename;

    /**
     * Constructor for Song class
     */
    public Song(String songName, String artist, double duration, String filename) {
        this.songName = songName;
        this.artist = artist;
        this.duration = duration;
        this.filename = filename;
    }

    /**
     * Returns the song name
     * 
     * @return        name of song
     */
    public String getSongName() {
        return songName;
    }
    
      /**
     * Returns the artist
     * 
     * @return        interpret of song
     */
    public String getArtist() {
        return artist;
    }
    
      /**
     * Returns the duration
     * 
     * @return        duration of song
     */
    public double getDuration() {
        return duration;
    }

    public String getFilename(){
        return filename;
    }

    public String toString(){
        return artist + " - " + songName + ": " + duration;
    }
}
