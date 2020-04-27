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
import sk.uniza.fri.worldOfFri.mapa.MiestnostNenajdena;

/**
 *
 * @author janik
 */
public interface IPredmet {
    String getNazov();
    void pouziSa(Hrac hrac);
    void ulozPoziciu(DataOutputStream pozicia) throws IOException;
    void nacitajPoziciu(DataInputStream pozicia, Budova budova, int verzia) throws IOException, MiestnostNenajdena;
}
