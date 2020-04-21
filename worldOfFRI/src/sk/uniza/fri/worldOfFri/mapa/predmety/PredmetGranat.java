/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.worldOfFri.mapa.predmety;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import sk.uniza.fri.worldOfFri.hlavny.Hrac;
import sk.uniza.fri.worldOfFri.mapa.Budova;
import sk.uniza.fri.worldOfFri.vynimky.SmrtException;

/**
 *
 * @author janik
 */
public class PredmetGranat implements IPredmet {

    private int pocetPouziti;
    
    public PredmetGranat() {
        this.pocetPouziti = 0;
    }

    @Override
    public String getNazov() {
        return "granat";
    }

    @Override
    public void pouziSa(Hrac hrac) {
        this.pocetPouziti++;
        if (this.pocetPouziti == 11) {
            System.out.println("BOOOM!");
            throw new SmrtException();
        } else if (this.pocetPouziti < 11) {
            System.out.println(11 - this.pocetPouziti);
        }
    }

    @Override
    public void ulozPoziciu(DataOutputStream pozicia) throws IOException {
        pozicia.writeInt(this.pocetPouziti);
    }

    @Override
    public void nacitajPoziciu(DataInputStream pozicia, Budova budova, int verzia) throws IOException {
        if (verzia >= 3) { // zaznamenavame stav granatu
            this.pocetPouziti = pozicia.readInt();
        }
    }
    
}
