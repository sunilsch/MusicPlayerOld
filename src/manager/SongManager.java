package manager;

import communication.HTTPSConnection;
import communication.SQLConnection;


public class SongManager {
    private final String serverIP;
    private final HTTPSConnection httpsHelper;
    private final SQLConnection sqlHelper;


    // connection with sql, https
    // gui new song window
    // controls song section of main window
    public SongManager(final String serverIP){
        this.serverIP = serverIP;
        this.httpsHelper = new HTTPSConnection(this.serverIP,"/files/","/upload/upload-java.php");
        this.sqlHelper = new SQLConnection();
    }
}
