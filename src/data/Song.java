package data;

/**
 * This class represents a song with name, artist and duration.
 *
 * @author Linus
 * @version 15.12 v.2.0 Update
 * <p>
 * Updated class to English
 */
public record Song(String songName, String artist, double duration, String filename) {

    @Override
    public String songName() {
        return songName;
    }

    @Override
    public String artist() {
        return artist;
    }

    @Override
    public double duration() {
        return duration;
    }

    public String toString() {
        return artist + " - " + songName + " : " + duration;
    }
}
