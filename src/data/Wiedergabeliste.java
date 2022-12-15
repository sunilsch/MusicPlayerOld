package data;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * Beschreiben Sie hier die Klasse Wiedergabeliste.
 * 
 * @author (WEI) 
 * @version (23.10.22, Schülerversion)
 */
public class Wiedergabeliste
{
    List<Titel> liste = new List<Titel>();
    int laenge;
    String name;
    SQL_Helper sqlHelper;
    Scanner scanner = new Scanner(System.in);
    /**
     * Konstruktor für Objekte der Klasse Wiedergabeliste
     */
    public Wiedergabeliste(String name, SQL_Helper sqlHelper)
    {
        laenge = 0;
        this.name = name;
        this.sqlHelper = sqlHelper;
    }
    
    public void titelSuchenUndAnfuegen(String name){
        ArrayList<Titel> titelList = sqlHelper.searchTitel("name", name);
        if(titelList.size() > 0){
            for(int i = 0; i < titelList.size(); i++){
                System.out.println((i+1)+". "+titelList.get(i));
            }
            int n;
            try {
                n = scanner.nextInt();
                n--;
                einfuegen(titelList.get(n));
            } catch(Exception e) {
                System.out.println("Try again");
                return;
            }
        }
        
    }
    public void einfuegen(Titel pNeuerTitel)
    {
        if(pNeuerTitel == null) return;
        liste.append(pNeuerTitel);
        laenge++;
        System.out.println("Anfügen von :  "+pNeuerTitel);
    }
    
    public void einfuegen(Titel pNeuerTitel, Titel pNachfolger) {
        if (pNachfolger == null) {
            liste.append(pNeuerTitel);
        } else {
            liste.toFirst();
            while (liste.hasAccess()
            && ! (liste.getContent() == pNachfolger)) {
                liste.next();
            }
            liste.insert(pNeuerTitel);
        }
    }
    
    public void songAbspielen()
    {
        if(liste.hasAccess()){
            System.out.println(gibAktuellesLied()+"wird abgespielt");
            liste.remove();
            laenge--;
        }
    }
    
    // Beispielmethode
    public String gibAktuellesLied()
    {
        if(!liste.hasAccess()){
            return "Kein Titel verfügbar";
        }
        return liste.getContent().toString();
    }
    
    public int gibLaenge(){
        return laenge;
    }
    
    
    public void run(){
        liste.toFirst();
        System.out.println("\nWas möchtest du tun?");
        System.out.println("1. Abspielen");
        System.out.println("2. Anfügen");
        System.out.println("3. Programm beenden");
        System.out.println("Nächster Song: ");
        System.out.println(gibAktuellesLied());
        System.out.println("\n------\n");
        int n = scanner.nextInt();
        if(n == 1){
            songAbspielen();
        } else if (n == 2){
            System.out.println("Nach Titel suchen (Name eingeben)");
            String name = scanner.next();
            titelSuchenUndAnfuegen(name);
        } else if (n == 3){
            return;
        } else {
            System.out.println("Nochmal versuchen: ");
        }
        System.out.println("----------------");
        run();
    }
    /**
     * Wenn der Parameter pLoeschTitel in der List titelListe gefunden wird 
     * dieser entfernt. Sonst passiert nichts.
     */
    public boolean loeschen(Titel pLoeschTitel)
    {
        liste.toFirst();
        while(liste.hasAccess()){
            if(liste.getContent() == pLoeschTitel){
                liste.remove();
                laenge--;
                return true;    
            }
            liste.next();
        }
        return false;
    }
    /**
     * Der Parameter pVerschiebeTitel darf nicht den Wert null haben. Das durch den Parameter pVerschiebeTitel angegebene Objekt 
     * wird vor die Position des durch den Parameter pNachfolger angegebenen Objekts in der Wiedergabeliste verschoben. 
     * Ist der Wert des Parameters pNachfolger null, so wird das durch pVerschiebeTitel angegebene Objekt an das Ende der Liste verschoben.
     */
    public void verschieben(Titel pVerschiebeTitel, Titel pNachfolger)
    {
        if(pVerschiebeTitel == null) return;
        if(!loeschen(pVerschiebeTitel)) return;
        if(pNachfolger == null) {
            einfuegen(pVerschiebeTitel);
        } else {
            einfuegen(pVerschiebeTitel, pNachfolger);
        }
    }
    
    public String getName(){
        return name;
    }
    
}
