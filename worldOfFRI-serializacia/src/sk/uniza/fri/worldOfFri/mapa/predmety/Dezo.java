/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.worldOfFri.mapa.predmety;

import sk.uniza.fri.worldOfFri.hlavny.Hrac;
import sk.uniza.fri.worldOfFri.hlavny.QuestPrechadzaniaMiestnostami;

/**
 *
 * Kedze nemame zatial ziadne postavy okrem hraca
 * Stary dezo, ktory bude davat quest bude predmet.
 */
public class Dezo implements IPredmet {

    private boolean dalQuest;

    public Dezo() {
        this.dalQuest = false;
    }
    
    @Override
    public String getNazov() {
        return "dezo";
    }

    @Override
    public void pouziSa(Hrac hrac) {
        if (this.dalQuest) {
            System.out.println("Dezo uz s tebou nic nechce mat");
        } else {
            System.out.println("Dezo chce, aby si presiel 5 miestnostami");
            hrac.getQuestbook().pridajQuest(new QuestPrechadzaniaMiestnostami(5));
            this.dalQuest = true;
        }
    }

}
