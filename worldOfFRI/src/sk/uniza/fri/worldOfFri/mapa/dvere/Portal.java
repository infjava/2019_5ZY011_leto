/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.worldOfFri.mapa.dvere;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import sk.uniza.fri.worldOfFri.hlavny.Hrac;
import sk.uniza.fri.worldOfFri.mapa.Budova;
import sk.uniza.fri.worldOfFri.mapa.Miestnost;
import sk.uniza.fri.worldOfFri.mapa.MiestnostNenajdena;

/**
 *
 * @author janik
 */
public class Portal implements IDvere {

    private final String nazov;
    private Portal portal;
    private Miestnost miestnost;

    public Portal(String nazov) {
        this.nazov = nazov;
        this.portal = null;
        this.miestnost = null;
    }

    @Override
    public Miestnost getVychod() {
        return this.portal.miestnost;
    }

    @Override
    public boolean mozePrejst() {
        return this.portal.miestnost != null;
    }

    @Override
    public void hracPresiel(Hrac hrac) {
        System.out.format("Presiel si %sm portalom%n", this.nazov);
    }

    public void prepoj(Portal portal) {
        this.portal = portal;
    }

    public void presunDo(Miestnost miestnost) {
        if (this.miestnost != null) {
            this.miestnost.zrusVychod(this.nazov);
        }
        
        this.miestnost = miestnost;
        
        this.miestnost.nastavVychod(this.nazov, this);
    }

    public String getNazov() {
        return this.nazov;
    }

    public void ulozPoziciu(DataOutputStream pozicia) throws IOException {
        if (this.miestnost != null) {
            pozicia.writeUTF(this.miestnost.getNazov());
        } else {
            pozicia.writeUTF("");
        }
    }

    public void nacitajPoziciu(DataInputStream pozicia, Budova budova, int verzia) throws IOException, MiestnostNenajdena {
        if (verzia >= 3) {
            if (this.miestnost != null) {
                this.miestnost.zrusVychod(this.nazov);
            }
            
            String nazovMiestnosti = pozicia.readUTF();
            if (nazovMiestnosti.isEmpty()) {
                this.miestnost = null;
            } else {
                this.miestnost = budova.getMiestnost(nazovMiestnosti);
                this.miestnost.nastavVychod(this.nazov, this);
            }
        }
    }
    
}
