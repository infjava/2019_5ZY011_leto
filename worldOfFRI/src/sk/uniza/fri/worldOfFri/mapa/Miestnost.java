package sk.uniza.fri.worldOfFri.mapa;

import java.util.Collection;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * Trieda Miestnost realizuje jednu miestnost/priestor v celom priestore hry.
 * Kazda "miestnost" je z inymi miestnostami spojena vychodmi. 
 * Vychody z miestnosti su oznacovane svetovymi stranami sever, vychod, juh
 * a zapad. Pre kazdy vychod si miestnost pamata odkaz na susednu miestnost
 * alebo null, ak tym smerom vychod nema.
 *
 * @author  Michael Kolling, David J. Barnes
 * @version 2006.03.30
 * @author  lokalizacia: Lubomir Sadlon, Jan Janech
 * @version 2012.02.21
 */
public class Miestnost {
    private String popisMiestnosti;
    private TreeMap<String, IDvere> vychody;
    private final HashMap<String, IPredmet> predmety;

    /**
     * Vytvori miestnost popis ktorej je v parametrom.
     * Po vytvoreni miestnost nema ziadne vychody. Popis miesnost strucne 
     * charakterizuje.
     * 
     * @param popis text popisu miestnosti.
     */
    public Miestnost(String popis) {
        this.popisMiestnosti = popis;
        this.vychody = new TreeMap<String, IDvere>();
        this.predmety = new HashMap<String, IPredmet>();
    }
    
    public void vypisInfo() {
        System.out.println("Teraz si v miestnosti " + this.getPopis());
        System.out.print("Vychody: ");
        for (String smer : this.vychody.keySet()) {
            System.out.print(smer + " ");
        }
        System.out.println();
    }

    /**
     * Nastavi vychody z miestnosti. Kazdy vychod je urceny bud odkazom 
     * na miestnost alebo hodnotou null, ak vychod tym smerom neexistuje.
     * 
     * @param smer nazov smeru.
     * @param vychod miestnost danym smerom.
     */
    public void nastavVychod(String smer, Miestnost vychod) {
        this.vychody.put(smer, new Dvere(vychod));
    }
    
    public void nastavVychod(String smer, IDvere dvere) {
        this.vychody.put(smer, dvere);
    }

    void zrusVychod(String smer) {
        this.vychody.remove(smer);
    }

    /**
     * @return textovy popis miestnosti.
     */
    public String getPopis() {
        return this.popisMiestnosti;
    }

    public IDvere getDvere(String smer) {
        return this.vychody.get(smer);
    }
    
    public Collection<IDvere> getVsetkyDvere() {
        return this.vychody.values();
    }

    public void polozPredmet(IPredmet predmet) {
        this.predmety.put(predmet.getNazov(), predmet);
    }

    public void vypisPrehliadku() {
        if (this.predmety.isEmpty()) {
            System.out.println("Nist som nenasiel");
        } else {
            System.out.println("Nasiel som:");
            for (String nazov : this.predmety.keySet()) {
                System.out.format("- %s%n", nazov);
            }
        }
    }

    public IPredmet zoberPredmet(String nazovPredmetu) {
        return this.predmety.remove(nazovPredmetu);
    }
}
