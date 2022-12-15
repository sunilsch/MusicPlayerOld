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

    private final List<Playlist> playlistList = new List<>();
    
    private final SQL_Helper sqlHelper = new SQL_Helper();

    /**
     * Constructor for PlaylistsManagement class
     */
    public PlaylistsManagement() {

    }
    
    public void createNewPlaylist(String name){
        playlistList.append(new Playlist(name, sqlHelper));
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
