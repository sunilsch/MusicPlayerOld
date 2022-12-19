package data;

/**
 * This class represents a Playlist
 * 
 * @author Linus
 * @version 15.12 v.2.0 Update
 *
 * Updated class to English
 */
public class Playlist {
    private final List<Song> playlist = new List<>();
    private int len;
    private final String listName;
    private final HTTPS_Helper httpsHelper;
    /**
     * Constructor for Playlist class
     */
    public Playlist(String listName, HTTPS_Helper httpsHelper) {
        this.len = 0;
        this.listName = listName;
        this.httpsHelper = httpsHelper;
    }

    public void insert(Song pNeuerSong) {
        if(pNeuerSong == null) return;
        playlist.append(pNeuerSong);
        len++;
        System.out.println("Anfügen von :  "+ pNeuerSong);
    }
    
    public void insert(Song newSong, Song followingSong) {
        if (followingSong == null) {
            playlist.append(newSong);
        } else {
            playlist.toFirst();
            while (playlist.hasAccess()
            && ! (playlist.getContent() == followingSong)) {
                playlist.next();
            }
            playlist.insert(newSong);
        }
    }
    
    public void playNextSong() {
        if(playlist.hasAccess()){
            System.out.println(getCurrentSong()+"wird abgespielt");
            playlist.remove();
            len--;
        }
    }
    
    // Beispielmethode
    public String getCurrentSong() {
        if(!playlist.hasAccess()){
            return "Kein Titel verfügbar";
        }
        return playlist.getContent().toString();
    }
    
    public int getLen(){
        return len;
    }

    public boolean delete(Song deleteSong) {
        playlist.toFirst();
        while(playlist.hasAccess()){
            if(playlist.getContent() == deleteSong){
                playlist.remove();
                len--;
                return true;    
            }
            playlist.next();
        }
        return false;
    }

    public void moveSong(Song moveSong, Song followingSong) {
        if(moveSong == null) return;
        if(!delete(moveSong)) return;
        if(followingSong == null) {
            insert(moveSong);
        } else {
            insert(moveSong, followingSong);
        }
    }
    
    public String getListName(){
        return listName;
    }
    
}