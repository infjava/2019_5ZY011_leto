/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.worldOfFri.hlavny;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * Trieda riadic questov ma za ulohu riadit stav questov. Preposiela im
 * vsetky akcie, ktore hrac v hre spravi - posun medzi miestnostami,
 * manipulacia s predmetmi, atd...
 */
public class RiadicQuestov implements Serializable {

    private final Questbook questbook;

    RiadicQuestov(Questbook questbook) {
        this.questbook = questbook;
    }
    
    public void hracSaPohol() {
        for (QuestPrechadzaniaMiestnostami quest : this.questbook.getQuesty()) {
            quest.hracSaPohol();
        }
        questbook.skontrolujStav();
    }
    
}
