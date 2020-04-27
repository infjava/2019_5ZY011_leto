/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.worldOfFri.mapa;

import java.util.Scanner;
import sk.uniza.fri.worldOfFri.mapa.dvere.InteligentneDvere;
import sk.uniza.fri.worldOfFri.mapa.dvere.VrazedneDvere;

/**
 *
 * @author janik
 */
class DefiniciaVychodu {

    private final Miestnost miestnost;
    private final Scanner riadok;

    DefiniciaVychodu(Miestnost miestnost, Scanner riadok) {
        this.miestnost = miestnost;
        this.riadok = riadok;
    }

    void vytvorSa(Budova budova) throws MiestnostNenajdena {
        String smer = this.riadok.next();
        switch (smer) {
            case "vrazedne:":
                smer = this.riadok.next();
                this.miestnost.nastavVychod(smer, new VrazedneDvere(budova.getMiestnost(riadok.nextLine().strip())));
                break;
            case "inteligentne:":
                smer = this.riadok.next();
                this.miestnost.nastavVychod(smer, new InteligentneDvere(budova.getMiestnost(riadok.nextLine().strip())));
                break;
            default:
                this.miestnost.nastavVychod(smer, budova.getMiestnost(riadok.nextLine().strip()));
                break;
        }

    }
    
}
