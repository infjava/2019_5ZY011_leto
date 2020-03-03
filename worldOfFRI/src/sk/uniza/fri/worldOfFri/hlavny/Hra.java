package sk.uniza.fri.worldOfFri.hlavny;

import sk.uniza.fri.worldOfFri.mapa.Budova;
import sk.uniza.fri.worldOfFri.prikazy.Parser;
import sk.uniza.fri.worldOfFri.prikazy.Prikaz;
import sk.uniza.fri.worldOfFri.prikazy.Prikazy;

/**
 * Trieda Hra je hlavna trieda aplikacie "World of FRI".
 * "World of FRI" je velmi jednoducha textova hra - adventura. 
 * Hrac sa moze prechadzat po niektorych priestoroch - miestnostiach fakulty. 
 * To je v tejto verzii vsetko. Hru treba skutocne zancne rozsirit,
 * aby bola zaujimava.
 * 
 * Ak chcete hrat "World of FRI", vytvorte instanciu triedy Hra (hra) 
 * a poslite jej spravu hraj.
 * 
 * Hra vytvori a inicializuje vsetky potebne objekty:
 * vytvori vsetky miestnosti, vytvori parser a zacne hru. Hra tiez vyhodnocuje
 * a vykonava prikazy, ktore vrati parser.
 * 
 * @author  Michael Kolling, David J. Barnes
 * @version 2006.03.30
 * @author  lokalizacia: Lubomir Sadlon, Jan Janech
 * @version 2012.02.21
*/
 
public class Hra  {
    private Parser parser;
    private final Hrac hrac;
    private final Budova budova;
    private final Prikazy prikazy;
    
    /**
     * Vytvori a inicializuje hru.
     */
    public Hra() {
        this.prikazy = new Prikazy();
        this.budova = new Budova();
        this.parser = new Parser(this.prikazy);
        this.hrac = new Hrac(this.budova.getStartovaciaMiestnost());
    }

    /**
     *  Hlavna metoda hry.
     *  Cyklicky opakuje kroky hry, kym hrac hru neukonci.
     */
    public void hraj() {            
        this.vypisPrivitanie();

        // Vstupny bod hlavneho cyklu.
        // Opakovane nacitava prikazy hraca
        // vykonava ich kym hrac nezada prikaz na ukoncenie hry.
                
        boolean jeKoniec;
        
        do {
            Prikaz prikaz = this.parser.nacitajPrikaz();
            jeKoniec = this.prikazy.vykonajPrikaz(this.hrac, prikaz);
        } while (!jeKoniec && this.hrac.getZije());
        
        if (this.hrac.getZije()) {
            System.out.println("Maj sa fajn!");
        } else {
            System.out.println("Pozdravuj pradeda.");
        }
    }

    /**
     * Vypise privitanie hraca do terminaloveho okna.
     */
    private void vypisPrivitanie() {
        System.out.println();
        System.out.println("Vitaj v hre World of FRI!");
        System.out.println("World of FRI je nova, neuveritelne nudna adventura.");
        System.out.println("Zadaj 'pomoc' ak potrebujes pomoc.");
        System.out.println();
        this.hrac.getAktualnaMiestnost().vypisInfo();
    }
}
