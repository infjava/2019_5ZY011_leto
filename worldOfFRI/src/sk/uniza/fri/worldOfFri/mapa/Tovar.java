/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.worldOfFri.mapa;

/**
 *
 * @author janik
 */
public class Tovar {

    private final IPredmet predmet;
    private final int cena;

    public Tovar(IPredmet predmet, int cena) {
        this.predmet = predmet;
        this.cena = cena;
    }
    
    public String getNazov() {
        return this.predmet.getNazov();
    }

    public IPredmet getPredmet() {
        return this.predmet;
    }

    public int getCena() {
        return this.cena;
    }
    
}
