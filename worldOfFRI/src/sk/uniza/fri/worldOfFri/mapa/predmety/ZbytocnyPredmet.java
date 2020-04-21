/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.worldOfFri.mapa.predmety;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import sk.uniza.fri.worldOfFri.hlavny.Hrac;
import sk.uniza.fri.worldOfFri.mapa.Budova;

/**
 *
 * @author janik
 */
public class ZbytocnyPredmet implements IPredmet {

    private final String nazov;

    public ZbytocnyPredmet(String nazov) {
        this.nazov = nazov;
    }

    @Override
    public String getNazov() {
        return this.nazov;
    }

    @Override
    public void pouziSa(Hrac hrac) {
        System.out.format("Predmet %s sa neda pouzit%n", this.nazov);
    }

    @Override
    public void ulozPoziciu(DataOutputStream pozicia) {
        
    }

    @Override
    public void nacitajPoziciu(DataInputStream pozicia, Budova budova, int verzia) {
        
    }
    
}
