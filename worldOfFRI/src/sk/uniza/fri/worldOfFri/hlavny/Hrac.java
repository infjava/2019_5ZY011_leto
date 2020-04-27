/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.worldOfFri.hlavny;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import sk.uniza.fri.worldOfFri.vynimky.NedaSaOdistException;
import sk.uniza.fri.worldOfFri.vynimky.NpcNespravnehoTypu;
import sk.uniza.fri.worldOfFri.vynimky.ZamknuteDvereException;
import sk.uniza.fri.worldOfFri.vynimky.NeexistujuciVychodException;
import sk.uniza.fri.worldOfFri.vynimky.NeexistujucaNpcException;
import java.util.HashMap;
import java.util.Scanner;
import sk.uniza.fri.worldOfFri.mapa.Budova;
import sk.uniza.fri.worldOfFri.mapa.npc.HostileNpc;
import sk.uniza.fri.worldOfFri.mapa.dvere.IDvere;
import sk.uniza.fri.worldOfFri.mapa.predmety.IPredmet;
import sk.uniza.fri.worldOfFri.mapa.Miestnost;
import sk.uniza.fri.worldOfFri.mapa.MiestnostNenajdena;
import sk.uniza.fri.worldOfFri.mapa.npc.Npc;
import sk.uniza.fri.worldOfFri.mapa.npc.Obchodnik;
import sk.uniza.fri.worldOfFri.mapa.npc.Tovar;
import sk.uniza.fri.worldOfFri.vynimky.SmrtException;

/**
 *
 * @author janik
 */
public class Hrac {
    private Miestnost aktualnaMiestnost;
    private final HashMap<String, IPredmet> inventar;
    private final Questbook questbook;
    private int zivoty;
    private int peniaze;

    public Hrac(Miestnost pociatocnaMiestnost) {
        this.aktualnaMiestnost = pociatocnaMiestnost;
        this.inventar = new HashMap<String, IPredmet>();
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
    
    public void prijmiUtok() {
        this.zivoty--;
        
        if (this.zivoty > 0) {
            System.out.format("Prijal si utok, mas este %d zivotov%n", this.zivoty);
        } else {
            throw new SmrtException();
        }
    }

    public void zautocNaNpc(String meno) throws NeexistujucaNpcException, NpcNespravnehoTypu {
        Npc npc = this.aktualnaMiestnost.getNpc(meno);
        
        if (!(npc instanceof HostileNpc)) {
            throw new NpcNespravnehoTypu();
        }
        
        HostileNpc hostileNpc = (HostileNpc) npc;
        hostileNpc.prijmiUtok();
    }

    public void nakupujOdNpc(String meno) throws NeexistujucaNpcException, NpcNespravnehoTypu {
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

    void ulozPoziciu(DataOutputStream pozicia) throws IOException {
        pozicia.writeInt(this.zivoty);
        pozicia.writeInt(this.peniaze);
        pozicia.writeUTF(this.aktualnaMiestnost.getNazov());
        pozicia.writeInt(this.inventar.size());
        for (IPredmet predmet : this.inventar.values()) {
            pozicia.writeUTF(predmet.getNazov());
            predmet.ulozPoziciu(pozicia);
        }
    }

    void nacitajPoziciu(DataInputStream pozicia, Budova budova, int verzia) throws IOException, MiestnostNenajdena {
        this.zivoty = pozicia.readInt();
        this.peniaze = pozicia.readInt();
        String nazovAktualnejMiestnosti = pozicia.readUTF();
        this.aktualnaMiestnost = budova.getMiestnost(nazovAktualnejMiestnosti);
        if (verzia >= 2) { // obsahuje inventar
            this.inventar.clear();
            int pocetPredmetov = pozicia.readInt();
            for (int i = 0; i < pocetPredmetov; i++) {
                String nazovPredmetu = pozicia.readUTF();
                final IPredmet predmet = budova.vytvorPredmet(nazovPredmetu);
                predmet.nacitajPoziciu(pozicia, budova, verzia);
                this.inventar.put(nazovPredmetu, predmet);
            }
        }
    }
    
}
