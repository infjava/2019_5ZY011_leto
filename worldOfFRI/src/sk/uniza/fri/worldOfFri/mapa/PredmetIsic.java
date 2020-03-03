/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.worldOfFri.mapa;


public class PredmetIsic implements IPredmet {

    @Override
    public String getNazov() {
        return "isic";
    }

    @Override
    public void pouziSa() {
        System.out.println("Otvaram dvere, pozor aby ta nebuchli");
    }
    
}
