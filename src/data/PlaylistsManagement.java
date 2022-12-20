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
    private Playlist activePlaylist = null;
    private int len = 0;

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
        if(len == 0){
            setActivePlaylist(name);
        }
        len++;
    }
    
    
    public boolean deletePlaylist(String name){
        playlistList.toFirst();
        while(playlistList.hasAccess()){
            if(playlistList.getContent().getListName().equals(name)){
                playlistList.remove();
                len--;
                if(len == 0) activePlaylist = null;
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

    public Playlist getActivePlaylist(){
        return activePlaylist;
    }

    public void setActivePlaylist(String name){
        if(len == 0){
            System.out.println("PlaylistList is empty");
            return;
        }
        playlistList.toFirst();
        while(playlistList.hasAccess()){
            if(playlistList.getContent().getListName().equals(name)){
                activePlaylist = playlistList.getContent();
                return;
            }
        }
    }

    public Song nextSong(){
        if(activePlaylist == null){
            System.out.println("There is no selected playlist");
            return null;
        } else {
            Song nextSong = activePlaylist.getAndRemoveNextSong();
            if(nextSong == null){
                System.out.println("There is no next Song");
                return null;
            }
            return nextSong;
        }
    }


}
