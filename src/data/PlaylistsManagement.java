package data;

/**
 * This class represents the PlaylistsManagement System
 * 
 * @author Linus
 * @version 15.12 v.2.0 Update
 *
 * Updated class to English
 */
public class PlaylistsManagement
{
    private final String serverIP;
    private final List<Playlist> playlistList;
    private final HTTPS_Helper httpsHelper;
    private final SQL_Helper sqlHelper;

    /**
     * Constructor for PlaylistsManagement class
     */
    public PlaylistsManagement(final String serverIP) {
        this.serverIP = serverIP;
        this.httpsHelper = new HTTPS_Helper(this.serverIP,"/files/","/upload/upload-java.php");
        this.sqlHelper = new SQL_Helper();
        this.playlistList = new List<>();
    }
    
    public void createNewPlaylist(String name){
        playlistList.append(new Playlist(name, httpsHelper));
    }
    
    
    public boolean deletePlaylist(String name){
        playlistList.toFirst();
        while(playlistList.hasAccess()){
            if(playlistList.getContent().getListName().equals(name)){
                playlistList.remove();
                return true;
            }
        }
        return false;
    }
    
    public Playlist getPlaylist(String name){
        playlistList.toFirst();
        while(playlistList.hasAccess()){
            if(playlistList.getContent().getListName().equals(name)){
                return playlistList.getContent();
            }
        }
        return null;
    }
}
