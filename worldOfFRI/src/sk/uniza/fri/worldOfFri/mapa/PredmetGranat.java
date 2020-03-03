/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.worldOfFri.mapa;

import sk.uniza.fri.worldOfFri.hlavny.Hrac;

/**
 *
 * @author janik
 */
public class PredmetGranat implements IPredmet {

    private int pocetPouziti;
    
    public PredmetGranat() {
        this.pocetPouziti = 0;
    }

    @Override
    public String getNazov() {
        return "granat";
    }

    @Override
    public void pouziSa(Hrac hrac) {
        this.pocetPouziti++;
        if (this.pocetPouziti == 11) {
            System.out.println("BOOOM!");
            hrac.umri();
        } else if (this.pocetPouziti < 11) {
            System.out.println(11 - this.pocetPouziti);
        }
    }
    
}
