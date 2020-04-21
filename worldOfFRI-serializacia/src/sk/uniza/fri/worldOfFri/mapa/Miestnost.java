package sk.uniza.fri.worldOfFri.mapa;

import sk.uniza.fri.worldOfFri.mapa.dvere.Dvere;
import sk.uniza.fri.worldOfFri.mapa.dvere.IDvere;
import sk.uniza.fri.worldOfFri.mapa.npc.HostileNpc;
import sk.uniza.fri.worldOfFri.mapa.npc.Npc;
import sk.uniza.fri.worldOfFri.mapa.predmety.IPredmet;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;
import sk.uniza.fri.worldOfFri.hlavny.Hrac;
import sk.uniza.fri.worldOfFri.vynimky.NeexistujucaNpcException;
import sk.uniza.fri.worldOfFri.vynimky.NeexistujuciVychodException;

/**
 * Trieda Miestnost realizuje jednu miestnost/priestor v celom priestore hry.
 * Kazda "miestnost" je z inymi miestnostami spojena vychodmi. Vychody z
 * miestnosti su oznacovane svetovymi stranami sever, vychod, juh a zapad. Pre
 * kazdy vychod si miestnost pamata odkaz na susednu miestnost alebo null, ak
 * tym smerom vychod nema.
 *
 * @author Michael Kolling, David J. Barnes
 * @version 2006.03.30
 * @author lokalizacia: Lubomir Sadlon, Jan Janech
 * @version 2012.02.21
 */
public class Miestnost {

    private String popisMiestnosti;
    private TreeMap<String, IDvere> vychody;
    private final HashMap<String, IPredmet> predmety;
    private final HashMap<String, Npc> npccka;

    /**
     * Vytvori miestnost popis ktorej je v parametrom. Po vytvoreni miestnost
     * nema ziadne vychody. Popis miesnost strucne charakterizuje.
     *
     * @param popis text popisu miestnosti.
     */
    public Miestnost(String popis) {
        this.popisMiestnosti = popis;
        this.vychody = new TreeMap<String, IDvere>();
        this.predmety = new HashMap<String, IPredmet>();
        this.npccka = new HashMap<String, Npc>();
    }

    public void vypisInfo() {
        System.out.println("Teraz si v miestnosti " + this.getPopis());
        this.vypisPolozky("Vychody", this.vychody.keySet());
        this.vypisPolozky("Npc", this.npccka.keySet());
    }

    private void vypisPolozky(String nadpis, Set<String> polozky) {
        if (!polozky.isEmpty()) {
            System.out.print(nadpis + ": ");
            for (String meno : polozky) {
                System.out.print(meno + " ");
            }
            System.out.println();
        }
    }

    /**
     * Nastavi vychody z miestnosti. Kazdy vychod je urceny bud odkazom na
     * miestnost alebo hodnotou null, ak vychod tym smerom neexistuje.
     *
     * @param smer nazov smeru.
     * @param vychod miestnost danym smerom.
     */
    public void nastavVychod(String smer, Miestnost vychod) {
        this.vychody.put(smer, new Dvere(vychod));
    }

    public void nastavVychod(String smer, IDvere dvere) {
        this.vychody.put(smer, dvere);
    }

    public void zrusVychod(String smer) {
        this.vychody.remove(smer);
    }

    /**
     * @return textovy popis miestnosti.
     */
    public String getPopis() {
        return this.popisMiestnosti;
    }

    public IDvere getDvere(String smer) throws NeexistujuciVychodException {
        IDvere dvere = this.vychody.get(smer);
        if (dvere == null) {
            throw new NeexistujuciVychodException();
        }
        return dvere;
    }

    public Collection<IDvere> getVsetkyDvere() {
        return this.vychody.values();
    }

    public void polozPredmet(IPredmet predmet) {
        this.predmety.put(predmet.getNazov(), predmet);
    }

    public void vypisPrehliadku() {
        if (this.predmety.isEmpty()) {
            System.out.println("Nist som nenasiel");
        } else {
            System.out.println("Nasiel som:");
            for (String nazov : this.predmety.keySet()) {
                System.out.format("- %s%n", nazov);
            }
        }
    }

    public IPredmet zoberPredmet(String nazovPredmetu) {
        return this.predmety.remove(nazovPredmetu);
    }

    void postavNpc(Npc npc) {
        this.npccka.put(npc.getMeno(), npc);
        npc.nastavMiestnost(this);
    }

    public boolean daSaOpustit() {
        for (Npc npc : this.npccka.values()) {
            if (npc instanceof HostileNpc) {
                return false;
            }
        }

        return true;
    }

    public Npc getNpc(String meno) throws NeexistujucaNpcException {
        Npc npc = this.npccka.get(meno);
        if (npc == null) {
            throw new NeexistujucaNpcException();
        }
        return npc;
    }

    public void odstranNpc(String meno) {
        this.npccka.remove(meno);
    }

    public void zautocitVsetkymiNpc(Hrac hrac) {
        for (Npc npc : this.npccka.values()) {
            if (npc instanceof HostileNpc) {
                ((HostileNpc) npc).zautoc(hrac);
            }
        }
    }
    
    public String getNazov() {
        String[] nazovAPopis = this.popisMiestnosti.split(" - ", 2);
        
        return nazovAPopis[0];
    }
}
