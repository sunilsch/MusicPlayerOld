package data;

/**
 * Beschreiben Sie hier die Klasse Titel.
 * 
 * @author (WI) 
 * @version (23.10.22 , Schülerversion)
 */
public class Titel
{
    private String liedname;
    private String interpret;
    private double dauer;

    /**
     * Konstruktor für Objekte der Klasse Titel
     */
    public Titel(String pLiedname, String pInterpret, double pDauer)
    {
        liedname = pLiedname;
        interpret = pInterpret;
        dauer = pDauer;
    }

    /**
     * Der Liedname wird zurückgegeben.
     * 
     * @return        liedname
     */
    public String gibLiedname() {
        return liedname;
    }
    
      /**
     * Der Interpret wird zurückgegeben.
     * 
     * @return        interpret
     */
    public String gibInterpret() {
        return interpret;
    }
    
      /**
     * Die Lieddauer wird zurückgegeben.
     * 
     * @return        liedname
     */
    public double gibDauer() {
        return dauer;
    }
    
    public String toString(){
        return interpret + " - " + liedname + ": " + dauer;
    }
}
