/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.worldOfFri.mapa;

import sk.uniza.fri.worldOfFri.hlavny.Hrac;


public class InteligentneDvere implements IDvere {

    private final Miestnost labak;
    private boolean odomknute;

    public InteligentneDvere(Miestnost labak) {
        this.labak = labak;
        this.odomknute = false;
    }

    @Override
    public Miestnost getVychod() {
        return this.labak;
    }

    @Override
    public boolean mozePrejst() {
        return this.odomknute;
    }

    void odomkni() {
        this.odomknute = true;
    }

    @Override
    public void hracPresiel(Hrac hrac) {
        this.odomknute = false;
    }
    
}
