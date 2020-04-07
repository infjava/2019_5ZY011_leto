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
public class Obchodnik extends Npc {

    private final Tovar[] inventar;

    public Obchodnik(String meno, Tovar... inventar) {
        super(meno);
        this.inventar = inventar;
    }

    public void vypisTovar() {
        System.out.println("Mam uz iba:");
        for (Tovar tovar : this.inventar) {
            System.out.format("- %s (%d zlatiek)%n", tovar.getNazov(), tovar.getCena());
        }
    }
    
}
