/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.worldOfFri.mapa;

import java.util.ArrayList;
import sk.uniza.fri.worldOfFri.mapa.npc.Npc;
import sk.uniza.fri.worldOfFri.mapa.npc.Obchodnik;
import sk.uniza.fri.worldOfFri.mapa.npc.Tovar;
import sk.uniza.fri.worldOfFri.mapa.predmety.IPredmet;

/**
 *
 * @author janik
 */
class DefiniciaObchodnika {

    private final String meno;
    private final ArrayList<Tovar> tovar;

    DefiniciaObchodnika(String meno) {
        this.meno = meno;
        this.tovar = new ArrayList<Tovar>();
    }

    void pridajTovar(IPredmet predmet, int cena) {
        this.tovar.add(new Tovar(predmet, cena));
    }

    Npc vytvorSa() {
        return new Obchodnik(meno, this.tovar.toArray(new Tovar[0]));
    }
    
}
