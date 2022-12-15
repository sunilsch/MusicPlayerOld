package data;

/**
 * Beschreiben Sie hier die Klasse Wiedergabelistenverwaltung.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Wiedergabelistenverwaltung
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private List<Wiedergabeliste> liste = new List<Wiedergabeliste>();
    
    private SQL_Helper sqlHelper = new SQL_Helper();
    /**
     * Konstruktor für Objekte der Klasse Wiedergabelistenverwaltung
     */
    public Wiedergabelistenverwaltung()
    {
        // Instanzvariable initialisieren
        
    }
    
    
    public void wiedergabelisteErstellen(String name){
        liste.append(new Wiedergabeliste(name, sqlHelper));
    }
    
    
    public boolean wiedergabelisteLöschen(String name){
        liste.toFirst();
        while(liste.hasAccess()){
            if(liste.getContent().getName().equals(name)){
                liste.remove();
                return true;
            }
        }
        return false;
    }
    
    public Wiedergabeliste wiedergabelisteHolen(String name){
        liste.toFirst();
        while(liste.hasAccess()){
            if(liste.getContent().getName().equals(name)){
                return liste.getContent();
            }
        }
        return null;
    }
    
    public static void main(String[] args){
        return;
    }
}
