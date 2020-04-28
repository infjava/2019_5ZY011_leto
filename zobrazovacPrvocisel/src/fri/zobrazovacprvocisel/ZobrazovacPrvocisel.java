/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.zobrazovacprvocisel;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author janik
 */
public class ZobrazovacPrvocisel {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Prvocisla p = new Prvocisla(10, 100);
        ZobrazovacPrvocisel.vypisVsetko(p);
        
        ArrayList<String> ar = new ArrayList<String>();
        ar.add("Tu");
        ar.add("som");
        ZobrazovacPrvocisel.vypisVsetko(ar);
    }
    
    public static <E> void vypisVsetko(Iterable<E> prvkyNaVypis) {
        for (E prvocislo : prvkyNaVypis) {
            System.out.println(prvocislo);
        }
    }
    
}
