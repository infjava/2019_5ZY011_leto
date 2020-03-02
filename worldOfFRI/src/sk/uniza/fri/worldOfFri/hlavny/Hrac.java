/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.worldOfFri.hlavny;

import java.util.HashMap;
import sk.uniza.fri.worldOfFri.mapa.Miestnost;
import sk.uniza.fri.worldOfFri.mapa.Predmet;

/**
 *
 * @author janik
 */
public class Hrac {
    private Miestnost aktualnaMiestnost;
    private final HashMap<String, Predmet> inventar;

    public Hrac(Miestnost pociatocnaMiestnost) {
        this.aktualnaMiestnost = pociatocnaMiestnost;
        this.inventar = new HashMap<String, Predmet>();
    }

    public Miestnost getAktualnaMiestnost() {
        return this.aktualnaMiestnost;
    }

    public boolean chodDanymSmerom(String smer) {
        Miestnost novaMiestnost = this.aktualnaMiestnost.getVychod(smer);

        if (novaMiestnost == null) {
            return false;
        }
        
        this.aktualnaMiestnost = novaMiestnost;
        return true;
    }

    public boolean zoberPredmet(String nazovPredmetu) {
        Predmet predmet = this.aktualnaMiestnost.zoberPredmet(nazovPredmetu);
        
        this.inventar.put(nazovPredmetu, predmet);
        return true;
    }
    
}
