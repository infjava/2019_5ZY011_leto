/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.uniza.fri.worldOfFri.mapa.npc;

import sk.uniza.fri.worldOfFri.hlavny.Hrac;


public class HostileNpc extends Npc {

    private int zivoty;

    public HostileNpc(String meno) {
        super(meno);
        
        this.zivoty = 5;
    }

    public void prijmiUtok() {
        this.zivoty--;
        
        if (this.zivoty > 0) {
            System.out.format("Zautocil si na %s a zobral si mu zivot, este ostava %d%n", this.getMeno(), this.zivoty);
        } else {
            System.out.format("Zabil si %s%n", this.getMeno());
            this.getAktualnaMiestnost().odstranNpc(this.getMeno());
        }
    }

    public void zautoc(Hrac hrac) {
        hrac.prijmiUtok();
    }
    
}
