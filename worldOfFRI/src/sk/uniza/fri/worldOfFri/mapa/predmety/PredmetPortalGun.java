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
import sk.uniza.fri.worldOfFri.mapa.dvere.Portal;

/**
 *
 * @author janik
 */
public class PredmetPortalGun implements IPredmet {

    private final Portal[] portaly;
    private int aktualny;

    public PredmetPortalGun() {
        this.portaly = new Portal[] {
            new Portal("modry"),
            new Portal("oranzovy")
        };
        this.portaly[0].prepoj(this.portaly[1]);
        this.portaly[1].prepoj(this.portaly[0]);
        this.aktualny = 0;
    }
    

    @Override
    public String getNazov() {
        return "portalgun";
    }

    @Override
    public void pouziSa(Hrac hrac) {
        this.portaly[this.aktualny].presunDo(hrac.getAktualnaMiestnost());
        
        System.out.format("Otvoril si %s portal%n", this.portaly[this.aktualny].getNazov());
        
        this.aktualny = (this.aktualny + 1) % this.portaly.length;
    }

    @Override
    public void ulozPoziciu(DataOutputStream pozicia) throws IOException {
        pozicia.writeInt(this.aktualny);
        this.portaly[0].ulozPoziciu(pozicia);
        this.portaly[1].ulozPoziciu(pozicia);
    }

    @Override
    public void nacitajPoziciu(DataInputStream pozicia, Budova budova, int verzia) throws IOException {
        if (verzia >= 3) {
            this.aktualny = pozicia.readInt();
            this.portaly[0].nacitajPoziciu(pozicia, budova, verzia);
            this.portaly[1].nacitajPoziciu(pozicia, budova, verzia);
        }
    }
    
}
