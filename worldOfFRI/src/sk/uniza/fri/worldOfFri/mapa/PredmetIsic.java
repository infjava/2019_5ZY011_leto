/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.worldOfFri.mapa;

import sk.uniza.fri.worldOfFri.hlavny.Hrac;


public class PredmetIsic implements IPredmet {

    @Override
    public String getNazov() {
        return "isic";
    }

    @Override
    public void pouziSa(Hrac hrac) {
        System.out.println("Otvaram dvere, pozor aby ta nebuchli");
    }
    
}
