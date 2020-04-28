/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.zobrazovacprvocisel;

import java.util.Iterator;

/**
 *
 * @author janik
 */
public class PrvocislaIterator implements Iterator<Integer> {

    private int aktualny;
    private final int koniec;

    PrvocislaIterator(int start, int koniec) {
        this.aktualny = start;
        this.koniec = koniec;
        
    }

    @Override
    public boolean hasNext() {
        return this.aktualny <= this.koniec;
    }

    @Override
    public Integer next() {
        int aktualny = this.aktualny;
        this.aktualny++;
        return aktualny;
    }
    
}
