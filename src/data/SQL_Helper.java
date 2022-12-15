import java.sql.* ;
import java.util.ArrayList;

/**
 * Beschreiben Sie hier die Klasse SQL_Helper.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class SQL_Helper
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private Connection connection;

    /**
     * Konstruktor für Objekte der Klasse SQL_Helper
     */
    public SQL_Helper()
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
    
    public ArrayList<Titel> searchTitel(String columnName, String pattern) {
        ArrayList<Titel> titel = new ArrayList<Titel>();
        try{
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM musik WHERE "+columnName+" LIKE '%"+pattern+"%'");
            while(result.next()){
                String name = result.getString("name");
                String interpret = result.getString("interpret");
                double dauer = Double.valueOf(result.getString("dauer"));
                titel.add(new Titel(name, interpret, dauer));
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
        return titel;
    }
}
