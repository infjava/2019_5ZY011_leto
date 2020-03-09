/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.worldOfFri.mapa;

import java.util.Random;
import sk.uniza.fri.worldOfFri.hlavny.Hrac;

/**
 *
 * @author janik
 */
public class VrazedneDvere implements IDvere {

    private final Miestnost vychod;

    public VrazedneDvere(Miestnost vychod) {
        this.vychod = vychod;
    }

    @Override
    public Miestnost getVychod() {
        return this.vychod;
    }

    @Override
    public boolean mozePrejst() {
        return true;
    }

    @Override
    public void hracPresiel(Hrac hrac) {
        Random rand = new Random();
        if (rand.nextBoolean()) {
            System.out.println("Zabili ta vrazedne dvere");
            hrac.umri();
        }
    }
    
}
