/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.worldOfFri.mapa;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
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

    private Miestnost startovaciaMiestnost;
    private final HashMap<String, Miestnost> miestnosti;

    public Budova() {
        this.miestnosti = new HashMap<String, Miestnost>();
        this.nacitajMapu("mapa.wofmap");
    }

    private void nacitajMapu(String nazovSuboru) {
        File mapaSubor = new File(nazovSuboru);
        
        try (Scanner mapa = new Scanner(mapaSubor)) {
            SekciaSuboru sekcia = null;
            Miestnost miestnost = null;
            ArrayList<DefiniciaVychodu> vychody = new ArrayList<DefiniciaVychodu>();
            
            while (mapa.hasNextLine()) {
                String celyRiadok = mapa.nextLine();
                
                if (celyRiadok.isBlank()) {
                    continue;
                }
                
                Scanner riadok = new Scanner(celyRiadok);
                
                switch (riadok.next()) {
                    case "Miestnost":
                        miestnost = this.newMiestnost(riadok.nextLine().strip());
                        break;
                    case "Vychody:":
                        sekcia = SekciaSuboru.VYCHODY;
                        break;
                    case "Npc:":
                        sekcia = SekciaSuboru.NPC;
                        break;
                    case "Predmety:":
                        sekcia = SekciaSuboru.PREDMETY;
                        break;
                    case "-":
                        switch (sekcia) {
                            case VYCHODY:
                                vychody.add(new DefiniciaVychodu(miestnost, riadok));
                                break;
                            case NPC:
                                switch (riadok.next()) {
                                    case "hostile":
                                        miestnost.postavNpc(new HostileNpc(riadok.nextLine().strip()));
                                        break;
                                    case "obchodnik":
                                        break;
                                    default:
                                        throw new AssertionError();
                                }
                                break;
                            case PREDMETY:
                                miestnost.polozPredmet(this.vytvorPredmet(riadok.nextLine().strip()));
                                break;
                            default:
                                throw new AssertionError();
                        }
                        break;
                    case "*":
                        break;
                    case "Start":
                        this.startovaciaMiestnost = this.getMiestnost(riadok.nextLine().strip());
                        break;
                    default:
                        throw new AssertionError();
                }
            }
            
            for (DefiniciaVychodu vychod : vychody) {
                vychod.vytvorSa(this);
            }
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("Subor s mapou sa nenasiel", ex);
        }
        
        if (this.startovaciaMiestnost == null) {
            throw new RuntimeException("Mapa neobsahuje ziadnu startovaciu miestnost");
        }
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
