/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.worldOfFri.mapa;

import java.util.HashMap;
import sk.uniza.fri.worldOfFri.mapa.npc.Tovar;
import sk.uniza.fri.worldOfFri.mapa.dvere.InteligentneDvere;
import sk.uniza.fri.worldOfFri.mapa.dvere.VrazedneDvere;
import sk.uniza.fri.worldOfFri.mapa.npc.Obchodnik;
import sk.uniza.fri.worldOfFri.mapa.npc.HostileNpc;
import sk.uniza.fri.worldOfFri.mapa.predmety.PredmetGranat;
import sk.uniza.fri.worldOfFri.mapa.predmety.Dezo;
import sk.uniza.fri.worldOfFri.mapa.predmety.IPredmet;
import sk.uniza.fri.worldOfFri.mapa.predmety.ZbytocnyPredmet;
import sk.uniza.fri.worldOfFri.mapa.predmety.PredmetIsic;
import sk.uniza.fri.worldOfFri.mapa.predmety.PredmetPortalGun;

/**
 *
 * @author janik
 */
public class Budova {

    private final Miestnost startovaciaMiestnost;
    private final HashMap<String, Miestnost> miestnosti;

    public Budova() {
        this.miestnosti = new HashMap<String, Miestnost>();
        
        Miestnost terasa = this.newMiestnost("terasa - hlavny vstup na fakultu");
        Miestnost vratnica = this.newMiestnost("vratnica - tu sidli p. vratnicka");
        Miestnost chodbickaB = this.newMiestnost("chodbicka b - mala prechodova miestnost, pozor na protiiducich");
        Miestnost chodbaB = this.newMiestnost("chodba b - tmava chodba; ktovie, co sa tu skryva");
        Miestnost labak = this.newMiestnost("labak - idealne miesto pre chovanie monstier");
        Miestnost chodbaA = this.newMiestnost("chodba a - svetla chodba plna pocitacov a vitriniek");
        Miestnost dekanat = this.newMiestnost("dekanat - odtialto vladne kral Dekan II");
        Miestnost ra006 = this.newMiestnost("ra006 - malicky labacik");
        Miestnost chodbaC = this.newMiestnost("chodba c - podzemna chodba do tajnych zakuti fakulty");
        Miestnost bufet = this.newMiestnost("bufet - rozvoniava tu vyprazany syr");
        
        terasa.nastavVychod("vychod", vratnica);
        terasa.nastavVychod("zapad", bufet);
        
        terasa.postavNpc(new HostileNpc("vlk"));
        
        terasa.polozPredmet(new PredmetGranat());
        
        vratnica.nastavVychod("zapad", terasa);
        vratnica.nastavVychod("sever", chodbaA);
        vratnica.nastavVychod("juh", chodbickaB);
        
        vratnica.postavNpc(new Obchodnik("bufetarka", 
            new Tovar(new ZbytocnyPredmet("bageta"), 10),
            new Tovar(new ZbytocnyPredmet("pizza"), 15),
            new Tovar(new ZbytocnyPredmet("horalka"), 30)
        ));
        
        vratnica.polozPredmet(new PredmetPortalGun());
        
        vratnica.polozPredmet(new PredmetIsic());
        
        vratnica.polozPredmet(new Dezo());
        
        chodbickaB.nastavVychod("sever", vratnica);
        chodbickaB.nastavVychod("juh", chodbaB);
        
        chodbaB.nastavVychod("sever", chodbickaB);
        chodbaB.nastavVychod("zapad", labak);
        
        labak.nastavVychod("vychod", chodbaB);
        
        chodbaA.nastavVychod("juh", vratnica);
        chodbaA.nastavVychod("zapad", ra006);
        chodbaA.nastavVychod("hore", new VrazedneDvere(dekanat));
        chodbaA.nastavVychod("dole", chodbaC);
        
        dekanat.nastavVychod("dole", chodbaA);
        
        ra006.nastavVychod("vychod", chodbaA);
        
        chodbaC.nastavVychod("hore", chodbaA);
        chodbaC.nastavVychod("zapad", bufet);
        
        bufet.nastavVychod("sever", terasa);
        bufet.nastavVychod("vychod", new InteligentneDvere(chodbaC));

        this.startovaciaMiestnost = vratnica;
    }

    public Miestnost getStartovaciaMiestnost() {
        return this.startovaciaMiestnost;
    }

    private Miestnost newMiestnost(String popis) {
        Miestnost miestnost = new Miestnost(popis);
        this.miestnosti.put(miestnost.getNazov(), miestnost);
        return miestnost;
    }
    
    public Miestnost getMiestnost(String nazov) {
        return this.miestnosti.get(nazov);
    }
    
    public IPredmet vytvorPredmet(String nazov) {
        switch (nazov) {
            case "granat":
                return new PredmetGranat();
            case "portalgun":
                return new PredmetPortalGun();
            case "isic":
                return new PredmetIsic();
            case "dezo":
                return new Dezo();
            default:
                return new ZbytocnyPredmet(nazov);
        }
    }
    
}
