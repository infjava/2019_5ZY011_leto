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
class Prvocisla implements Iterable<Integer> {

    private final int start;
    private final int koniec;

    Prvocisla(int start, int koniec) {
        this.start = start;
        this.koniec = koniec;
        
    }

    @Override
    public Iterator<Integer> iterator() {
        return new PrvocislaIterator(this.start, this.koniec);
    }
    
}
