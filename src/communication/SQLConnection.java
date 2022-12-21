package communication;

import data.Song;

import java.sql.* ;
import java.util.ArrayList;

/**
 * This class connects to an SQL database and can search for Songs
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class SQLConnection
{

    private Connection connection;

    /**
     * Constructor for SQL_Helper class
     */
    public SQLConnection()
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/datenbank?user=root&password=p@ssword" );
        } catch( SQLException se ) {
            System.out.println( "SQL Exception:" ) ;
            while( se != null ) {
              System.out.println( "State  : " + se.getSQLState()  ) ;
              System.out.println( "Message: " + se.getMessage()   ) ;
              System.out.println( "Error  : " + se.getErrorCode() ) ;
    
              se = se.getNextException() ;
            }
       } catch( Exception e ) {
            System.out.println( e ) ;
       }
            
    }
    
    public ArrayList<Song> searchSong(String columnName, String pattern) {
        ArrayList<Song> song = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM musik WHERE "+columnName+" LIKE '%"+pattern+"%'");
            while(result.next()){
                String name = result.getString("name");
                String interpret = result.getString("interpret");
                double duration = Double.parseDouble(result.getString("dauer"));
                String filename = result.getString("filename");
                song.add(new Song(name, interpret, duration,filename));
            }
            result.close();
            statement.close();
        } catch(SQLException se){
            System.out.println("SQL Exception:") ;
            while( se != null ) {
              System.out.println( "State  : " + se.getSQLState()  ) ;
              System.out.println( "Message: " + se.getMessage()   ) ;
              System.out.println( "Error  : " + se.getErrorCode() ) ;
              se = se.getNextException() ;
            }
        } catch(Exception e){
            System.out.println(e) ;
        }
        return song;
    }
}
