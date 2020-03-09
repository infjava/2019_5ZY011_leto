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
class Dvere {

    private final Miestnost vychod;

    Dvere(Miestnost vychod) {
        this.vychod = vychod;
        
    }

    Miestnost getVychod() {
        return this.vychod;
    }
    
}
