package sk.uniza.fri.worldOfFri.prikazy;

import sk.uniza.fri.worldOfFri.hlavny.Hra;
import sk.uniza.fri.worldOfFri.hlavny.Hrac;
import sk.uniza.fri.worldOfFri.vynimky.ChybaPriSpracovaniSave;
import sk.uniza.fri.worldOfFri.vynimky.NedaSaOdistException;
import sk.uniza.fri.worldOfFri.vynimky.NeexistujucaNpcException;
import sk.uniza.fri.worldOfFri.vynimky.NeexistujuciVychodException;
import sk.uniza.fri.worldOfFri.vynimky.NpcNespravnehoTypu;
import sk.uniza.fri.worldOfFri.vynimky.ZamknuteDvereException;

/**
 * Trieda NazvyPrikazov udrzuje zoznam nazvov platnych prikazov hry. 
 * Za ulohu ma rozpoznavat platne prikazy.
 *
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 * @author  lokalizacia: Lubomir Sadlon, Jan Janech
 * @version 2012.02.21
 */

public class Prikazy {
    // konstantne pole nazvov prikazov
    private static final String[] PLATNE_PRIKAZY = {
        "chod", "ukonci", "pomoc", "hladaj", "zober", "inventar",
        "zahod", "pouzi", "questbook", "zautoc", "nakupuj",
        "save"
    };
    
    private final Hra hra;

    public Prikazy(Hra hra) {
        this.hra = hra;
    }

    /**
     * Kontroluje, ci nazov v parametri je platny prikaz.
     *  
     * @return true ak je parameter platny prikaz,
     * false inak.
     */
    public boolean jePrikaz(String nazov) {
        for (int i = 0; i < Prikazy.PLATNE_PRIKAZY.length; i++) {
            if (Prikazy.PLATNE_PRIKAZY[i].equals(nazov)) {
                return true;
            }
        }
        // ak algoritmus dosiahne tento bod, parameter nie je platny prikaz
        return false;
    }

    /**
     * Prevezne prikaz a vykona ho.
     * 
     * @param hrac hrac, ktory vykonava prikaz.
     * @param prikaz prikaz, ktory ma byt vykonany.
     * @return true ak prikaz ukonci hru, inak vrati false.
     */
    public boolean vykonajPrikaz(Hrac hrac, Prikaz prikaz) {
        boolean jeKoniec = false;

        if (prikaz.jeNeznamy()) {
            System.out.println("Nerozumiem, co mas na mysli...");
            return false;
        }

        String nazovPrikazu = prikaz.getNazov();
        
        switch (nazovPrikazu) {
            case "pomoc":
                this.vypisNapovedu();
                return false;
            case "chod":
                this.chodDoMiestnosti(hrac, prikaz);
                return false;
            case "ukonci":
                return this.ukonciHru(prikaz);
            case "hladaj":
                this.prehladajMiestnost(hrac);
                return false;
            case "zober":
                this.zoberPredmet(hrac, prikaz);
                return false;
            case "inventar":
                this.ukazInventar(hrac);
                return false;
            case "zahod":
                this.zahodPredmet(hrac, prikaz);
                return false;
            case "pouzi":
                this.pouziPredmet(hrac, prikaz);
                return false;
            case "questbook":
                this.zobrazQuestbook(hrac);
                return false;
            case "zautoc":
                this.zautocNaNpc(hrac, prikaz);
                return false;
            case "nakupuj":
                this.nakupujOdNpc(hrac, prikaz);
                return false;
            case "save":
                this.ulozPoziciu(hrac, prikaz);
                return false;
            default:
                return false;
        }
    }

    // implementacie prikazov:

    /**
     * Vypise text pomocnika do terminaloveho okna.
     * Text obsahuje zoznam moznych prikazov.
     */
    private void vypisNapovedu() {
        System.out.println("Zabludil si. Si sam. Tulas sa po fakulte.");
        System.out.println();
        System.out.println("Mozes pouzit tieto prikazy:");
        System.out.println("   chod ukonci pomoc");
    }

    /** 
     * Vykona pokus o prechod do miestnosti urcenej danym smerom.
     * Ak je tym smerom vychod, hrac prejde do novej miestnosti.
     * Inak sa vypise chybova sprava do terminaloveho okna.
     */
    private void chodDoMiestnosti(Hrac hrac, Prikaz prikaz) {
        if (!prikaz.maParameter()) {
            // ak prikaz nema parameter - druhe slovo - nevedno kam ist
            System.out.println("Chod kam?");
            return;
        }

        String smer = prikaz.getParameter();
        
        try {
            hrac.chodDanymSmerom(smer);
            hrac.getAktualnaMiestnost().vypisInfo();
        } catch (NedaSaOdistException ex) {
            System.out.println("Teraz nemozes odist!");
        } catch (NeexistujuciVychodException ex) {
            System.out.println("Tam nie je vychod!");
        } catch (ZamknuteDvereException ex) {
            System.out.println("Cez dvere sa neda prejst.");
        }
    }

    /** 
     * Ukonci hru.
     * Skotroluje cely prikaz a zisti, ci je naozaj koniec hry.
     * Prikaz ukoncenia nema parameter.
     * 
     * @return true, if this command quits the game, false otherwise.
     * @return true, ak prikaz konci hru, inak false.
     */
    private boolean ukonciHru(Prikaz prikaz) {
        if (prikaz.maParameter()) {
            System.out.println("Ukonci, co?");
            return false;
        } else {
            return true;
        }
    }

    private void prehladajMiestnost(Hrac hrac) {
        hrac.getAktualnaMiestnost().vypisPrehliadku();
    }

    private void zoberPredmet(Hrac hrac, Prikaz prikaz) {
        String nazovPredmetu = prikaz.getParameter();
        if (!hrac.zoberPredmet(nazovPredmetu)) {
            System.out.format("Nenasiel som %s!%n", nazovPredmetu);
        }
    }

    private void ukazInventar(Hrac hrac) {
        hrac.vypisInventar();
    }

    private void zahodPredmet(Hrac hrac, Prikaz prikaz) {
        String nazovPredmetu = prikaz.getParameter();
        if (!hrac.zahodPredmet(nazovPredmetu)) {
            System.out.format("No sorry, ale predmet %s nemas!%n", nazovPredmetu);
        }
    }

    private void pouziPredmet(Hrac hrac, Prikaz prikaz) {
        String nazovPredmetu = prikaz.getParameter();
        if (!hrac.pouziPredmet(nazovPredmetu)) {
            System.out.format("No sorry, ale predmet %s nemas!%n", nazovPredmetu);
        }
    }

    private void zobrazQuestbook(Hrac hrac) {
        hrac.getQuestbook().vypisQuesty();
    }

    private void zautocNaNpc(Hrac hrac, Prikaz prikaz) {
        String meno = prikaz.getParameter();
        try {
            hrac.zautocNaNpc(meno);
        } catch (NeexistujucaNpcException ex) {
            System.out.format("Npc %s nide nevidis!%n", meno);
        } catch (NpcNespravnehoTypu ex) {
            System.out.format("Npc %s nie je nepriatel!%n", meno);
        }
    }

    private void nakupujOdNpc(Hrac hrac, Prikaz prikaz) {
        String meno = prikaz.getParameter();
        try {
            hrac.nakupujOdNpc(meno);
        } catch (NeexistujucaNpcException ex) {
            System.out.format("Npc %s nide nevidis!%n", meno);
        } catch (NpcNespravnehoTypu ex) {
            System.out.format("Npc %s nie je obchodnikom!%n", meno);
        }
    }

    private void ulozPoziciu(Hrac hrac, Prikaz prikaz) {
        String nazov_pozicie = prikaz.getParameter();
        
        try {
            this.hra.ulozPoziciu(nazov_pozicie);
        } catch (ChybaPriSpracovaniSave ex) {
            System.out.println("Nepodarilo sa ulozit save, skus znovu");
        }
    }
}
