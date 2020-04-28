/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.klietkanazvierata;

/**
 *
 * @author janik
 */
public class Lev extends Zviera {

    @Override
    public String toString() {
        return "Lev";
    }
    
    @Override
    public void zjedz(IPotrava<Zviera> potrava) {
        System.out.println("Lev hlasito zozral " + potrava);
    }
    
}
