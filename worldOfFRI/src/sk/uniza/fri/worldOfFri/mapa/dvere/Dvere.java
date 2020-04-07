/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.worldOfFri.mapa.dvere;

import sk.uniza.fri.worldOfFri.hlavny.Hrac;
import sk.uniza.fri.worldOfFri.mapa.Miestnost;

/**
 *
 * @author janik
 */
public class Dvere implements IDvere {

    private final Miestnost vychod;

    public Dvere(Miestnost vychod) {
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
        
    }
}
