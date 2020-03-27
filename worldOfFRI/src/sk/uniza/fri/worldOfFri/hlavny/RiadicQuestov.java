/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.worldOfFri.hlavny;

import java.util.ArrayList;

/**
 *
 * Trieda riadic questov ma za ulohu riadit stav questov. Preposiela im
 * vsetky akcie, ktore hrac v hre spravi - posun medzi miestnostami,
 * manipulacia s predmetmi, atd...
 */
public class RiadicQuestov {

    private final ArrayList<QuestPrechadzaniaMiestnostami> questy;

    RiadicQuestov(ArrayList<QuestPrechadzaniaMiestnostami> questy) {
        this.questy = questy;
    }
    
    public void hracSaPohol() {
        for (QuestPrechadzaniaMiestnostami quest : this.questy) {
            quest.hracSaPohol();
        }
    }
    
}
