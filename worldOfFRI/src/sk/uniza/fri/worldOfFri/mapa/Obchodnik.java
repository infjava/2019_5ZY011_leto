/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.worldOfFri.mapa;

import java.util.HashMap;

/**
 *
 * @author janik
 */
public class Obchodnik extends Npc {

    private final HashMap<String, Tovar> inventar;

    public Obchodnik(String meno, Tovar... inventar) {
        super(meno);
        this.inventar = new HashMap<String, Tovar>();
        
        for (Tovar tovar : inventar) {
            this.inventar.put(tovar.getNazov(), tovar);
        }
    }

    public void vypisTovar() {
        System.out.println("Mam uz iba:");
        for (Tovar tovar : this.inventar.values()) {
            System.out.format("- %s (%d zlatiek)%n", tovar.getNazov(), tovar.getCena());
        }
    }

    public Tovar kup(String nazovPredmetu, int maximalnaCena) {
        Tovar tovar = this.inventar.get(nazovPredmetu);
        if (tovar.getCena() > maximalnaCena) {
            return null; // tovar s takymto nazvom za tuto cenu nemame
        }
        return this.inventar.remove(nazovPredmetu);
    }
    
}
