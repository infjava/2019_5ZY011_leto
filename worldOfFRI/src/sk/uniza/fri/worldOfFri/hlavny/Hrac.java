/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.worldOfFri.hlavny;

import java.util.HashMap;
import java.util.Scanner;
import sk.uniza.fri.worldOfFri.mapa.npc.HostileNpc;
import sk.uniza.fri.worldOfFri.mapa.dvere.IDvere;
import sk.uniza.fri.worldOfFri.mapa.predmety.IPredmet;
import sk.uniza.fri.worldOfFri.mapa.Miestnost;
import sk.uniza.fri.worldOfFri.mapa.npc.Npc;
import sk.uniza.fri.worldOfFri.mapa.npc.Obchodnik;
import sk.uniza.fri.worldOfFri.mapa.npc.Tovar;

/**
 *
 * @author janik
 */
public class Hrac {
    private Miestnost aktualnaMiestnost;
    private final HashMap<String, IPredmet> inventar;
    private boolean zije;
    private final Questbook questbook;
    private int zivoty;
    private int peniaze;

    public Hrac(Miestnost pociatocnaMiestnost) {
        this.aktualnaMiestnost = pociatocnaMiestnost;
        this.inventar = new HashMap<String, IPredmet>();
        this.zije = true;
        this.questbook = new Questbook();
        this.zivoty = 10;
        this.peniaze = 20;
    }

    public Miestnost getAktualnaMiestnost() {
        return this.aktualnaMiestnost;
    }

    public Questbook getQuestbook() {
        return this.questbook;
    }

    public void chodDanymSmerom(String smer)
            throws NedaSaOdistException, NeexistujuciVychodException, ZamknuteDvereException {
        if (!this.aktualnaMiestnost.daSaOpustit()) {
            throw new NedaSaOdistException();
        }
        
        IDvere dvereVSmere = this.aktualnaMiestnost.getDvere(smer);

        if (!dvereVSmere.mozePrejst()) {
            throw new ZamknuteDvereException();
        }
        
        this.aktualnaMiestnost = dvereVSmere.getVychod();
        dvereVSmere.hracPresiel(this);
        
        this.questbook.getRiadic().hracSaPohol();
    }

    public boolean zoberPredmet(String nazovPredmetu) {
        IPredmet predmet = this.aktualnaMiestnost.zoberPredmet(nazovPredmetu);
        
        if (predmet == null) {
            return false;
        }
        
        this.inventar.put(nazovPredmetu, predmet);
        return true;
    }

    public void vypisInventar() {
        if (this.inventar.isEmpty()) {
            System.out.println("Inventar je prazdny");
        } else {
            System.out.println("V inventari mas:");
            for (String nazov : this.inventar.keySet()) {
                System.out.format("- %s%n", nazov);
            }
        }
        System.out.format("Mas: %d zlatiek%n", this.peniaze);
    }

    public boolean zahodPredmet(String nazovPredmetu) {
        IPredmet predmet = this.inventar.remove(nazovPredmetu);
        
        if (predmet == null) {
            return false;
        }
        
        this.aktualnaMiestnost.polozPredmet(predmet);
        return true;
    }

    public boolean pouziPredmet(String nazovPredmetu) {
        IPredmet predmet = this.inventar.get(nazovPredmetu);
        
        if (predmet == null) {
            return false;
        }
        
        predmet.pouziSa(this);
        return true;
    }

    public boolean getZije() {
        return this.zije;
    }

    public void umri() {
        this.zije = false;
    }
    
    public void prijmiUtok() {
        this.zivoty--;
        
        if (this.zivoty > 0) {
            System.out.format("Prijal si utok, mas este %d zivotov%n", this.zivoty);
        } else {
            this.umri();
        }
    }

    public void zautocNaNpc(String meno) throws NeexistujucaNpc, NpcNespravnehoTypu {
        Npc npc = this.aktualnaMiestnost.getNpc(meno);
        
        if (!(npc instanceof HostileNpc)) {
            throw new NpcNespravnehoTypu();
        }
        
        HostileNpc hostileNpc = (HostileNpc) npc;
        hostileNpc.prijmiUtok();
    }

    public void nakupujOdNpc(String meno) throws NeexistujucaNpc, NpcNespravnehoTypu {
        Npc npc = this.aktualnaMiestnost.getNpc(meno);
        
        if (!(npc instanceof Obchodnik)) {
            throw new NpcNespravnehoTypu();
        }
        
        Obchodnik obchodnik = ((Obchodnik) npc);
        obchodnik.vypisTovar();

        Tovar tovar;
        do {                
            System.out.print("co kupis> ");
            Scanner vstup = new Scanner(System.in);
            String nazovPredmetu = vstup.nextLine();

            if (nazovPredmetu.equals("nic")) {
                return;
            }

            tovar = obchodnik.kup(nazovPredmetu, this.peniaze);
        } while (tovar == null);

        this.peniaze -= tovar.getCena();
        this.inventar.put(tovar.getPredmet().getNazov(), tovar.getPredmet());
    }
    
}
