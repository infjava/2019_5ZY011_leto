/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.worldOfFri.mapa;


public class InteligentneDvere implements IDvere {

    private final Miestnost labak;

    public InteligentneDvere(Miestnost labak) {
        this.labak = labak;
    }

    @Override
    public Miestnost getVychod() {
        return this.labak;
    }
    
}
