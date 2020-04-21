/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.worldOfFri.mapa.npc;

import java.io.Serializable;
import sk.uniza.fri.worldOfFri.mapa.Miestnost;

/**
 *
 * @author janik
 */
public abstract class Npc implements Serializable {

    private final String meno;
    private Miestnost aktualnaMiestnost;

    Npc(String meno) {
        this.meno = meno;
        this.aktualnaMiestnost = null;
    }

    public String getMeno() {
        return this.meno;
    }

    public void nastavMiestnost(Miestnost miestnost) {
        this.aktualnaMiestnost = miestnost;
    }

    public Miestnost getAktualnaMiestnost() {
        return this.aktualnaMiestnost;
    }
}
