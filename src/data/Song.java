package data;

/**
 * This class represents a song with name, artist and duration.
 *
 * @author Linus
 * @version 15.12 v.2.0 Update
 *
 */

public record Song(String songName, String artist, String filename) {

    @Override
    public String songName() {
        return songName;
    }

    @Override
    public String artist() {
        return artist;
    }

    @Override
    public String filename() {
        return filename;
    }

    public String toString() {
        return artist + " - " + songName;
    }
}
