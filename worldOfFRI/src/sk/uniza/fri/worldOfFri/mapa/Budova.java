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
public class Budova {

    private final Miestnost startovaciaMiestnost;

    public Budova() {
        Miestnost terasa = new Miestnost("terasa - hlavny vstup na fakultu");
        Miestnost vratnica = new Miestnost("vratnica - tu sidli p. vratnicka");
        Miestnost chodbickaB = new Miestnost("chodbicka b - mala prechodova miestnost, pozor na protiiducich");
        Miestnost chodbaB = new Miestnost("chodba b - tmava chodba; ktovie, co sa tu skryva");
        Miestnost labak = new Miestnost("labak - idealne miesto pre chovanie monstier");
        Miestnost chodbaA = new Miestnost("chodba a - svetla chodba plna pocitacov a vitriniek");
        Miestnost dekanat = new Miestnost("dekanat - odtialto vladne kral Dekan II");
        Miestnost ra006 = new Miestnost("ra006 - malicky labacik");
        Miestnost chodbaC = new Miestnost("chodba c - podzemna chodba do tajnych zakuti fakulty");
        Miestnost bufet = new Miestnost("bufet - rozvoniava tu vyprazany syr");
        
        terasa.nastavVychod("vychod", vratnica);
        terasa.nastavVychod("zapad", bufet);
        
        vratnica.nastavVychod("zapad", terasa);
        vratnica.nastavVychod("sever", chodbaA);
        vratnica.nastavVychod("juh", chodbickaB);
        
        vratnica.polozPredmet(new Predmet("isic"));
        
        chodbickaB.nastavVychod("sever", vratnica);
        chodbickaB.nastavVychod("juh", chodbaB);
        
        chodbaB.nastavVychod("sever", chodbickaB);
        chodbaB.nastavVychod("zapad", labak);
        
        labak.nastavVychod("vychod", chodbaB);
        
        chodbaA.nastavVychod("juh", vratnica);
        chodbaA.nastavVychod("zapad", ra006);
        chodbaA.nastavVychod("hore", dekanat);
        chodbaA.nastavVychod("dole", chodbaC);
        
        dekanat.nastavVychod("dole", chodbaA);
        
        ra006.nastavVychod("vychod", chodbaA);
        
        chodbaC.nastavVychod("hore", chodbaA);
        chodbaC.nastavVychod("zapad", bufet);
        
        bufet.nastavVychod("sever", terasa);
        bufet.nastavVychod("vychod", chodbaC);

        this.startovaciaMiestnost = vratnica;
    }

    public Miestnost getStartovaciaMiestnost() {
        return this.startovaciaMiestnost;
    }
    
}
