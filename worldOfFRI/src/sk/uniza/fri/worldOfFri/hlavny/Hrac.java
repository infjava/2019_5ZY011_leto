/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.worldOfFri.hlavny;

import java.util.HashMap;
import sk.uniza.fri.worldOfFri.mapa.IDvere;
import sk.uniza.fri.worldOfFri.mapa.IPredmet;
import sk.uniza.fri.worldOfFri.mapa.Miestnost;

/**
 *
 * @author janik
 */
public class Hrac {
    private Miestnost aktualnaMiestnost;
    private final HashMap<String, IPredmet> inventar;
    private boolean zije;

    public Hrac(Miestnost pociatocnaMiestnost) {
        this.aktualnaMiestnost = pociatocnaMiestnost;
        this.inventar = new HashMap<String, IPredmet>();
        this.zije = true;
    }

    public Miestnost getAktualnaMiestnost() {
        return this.aktualnaMiestnost;
    }

    public boolean chodDanymSmerom(String smer) {
        IDvere dvereVSmere = this.aktualnaMiestnost.getDvere(smer);

        if (dvereVSmere == null) {
            return false;
        }
        
        if (!dvereVSmere.mozePrejst()) {
            return false;
        }
        
        this.aktualnaMiestnost = dvereVSmere.getVychod();
        return true;
    }

    public boolean zoberPredmet(String nazovPredmetu) {
        IPredmet predmet = this.aktualnaMiestnost.zoberPredmet(nazovPredmetu);
        
        if (predmet == null) {
            return false;
        }
        
        this.inventar.put(nazovPredmetu, predmet);
        return true;
    }

    public void vypisInventar() {
        if (this.inventar.isEmpty()) {
            System.out.println("Inventar je prazdny");
        } else {
            System.out.println("V inventari mas:");
            for (String nazov : this.inventar.keySet()) {
                System.out.format("- %s%n", nazov);
            }
        }
    }

    public boolean zahodPredmet(String nazovPredmetu) {
        IPredmet predmet = this.inventar.remove(nazovPredmetu);
        
        if (predmet == null) {
            return false;
        }
        
        this.aktualnaMiestnost.polozPredmet(predmet);
        return true;
    }

    public boolean pouziPredmet(String nazovPredmetu) {
        IPredmet predmet = this.inventar.get(nazovPredmetu);
        
        if (predmet == null) {
            return false;
        }
        
        predmet.pouziSa(this);
        return true;
    }

    public boolean getZije() {
        return this.zije;
    }

    public void umri() {
        this.zije = false;
    }
    
}
