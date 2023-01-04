package manager;

import communication.HTTPSConnection;
import communication.SQLConnection;
import data.Song;

import java.util.ArrayList;

public class SongManager {
    private final String serverIP;
    private final HTTPSConnection httpsHelper;
    private final SQLConnection sqlHelper;


    // connection with sql, https
    // gui new song window
    // controls song section of main window
    public SongManager(final String serverIP) {
        this.serverIP = serverIP;
        this.httpsHelper = new HTTPSConnection(this.serverIP,"/files/","/upload/upload-java.php");
        this.sqlHelper = new SQLConnection(serverIP);
        httpsHelper.getSong("test.wav");
    }

    public ArrayList<Song> search(String searchString){
        // search song via sql connection
        return new ArrayList<Song>();
    }

    public void downloadSong(){
        // download song via https to ./audio/
    }

    public void uploadSong(){
        // upload song via https to server
        // -> Select file via JFileChooser
        // -> Upload
        // -> Create SQL-Entry
    }

    public void deleteSongOnClient(){
        // delete file in /audio/
    }

    public void deleteSongOnServer(){
        // -> First create application on server to delete
        // -> Maybe https (DELETE) statement
    }

}
