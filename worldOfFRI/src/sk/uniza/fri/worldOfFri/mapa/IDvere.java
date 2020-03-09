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
public interface IDvere {

    Miestnost getVychod();
    boolean mozePrejst();
    void hracPresiel(Hrac hrac);
    
}
