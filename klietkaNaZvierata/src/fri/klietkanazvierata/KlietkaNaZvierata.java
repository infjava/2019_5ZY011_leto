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
public class KlietkaNaZvierata {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Klietka<Lev> klietkaNaLeva = new Klietka<Lev>();
        System.out.println(klietkaNaLeva);
        klietkaNaLeva.vloz(new Lev());
        System.out.println(klietkaNaLeva);
        
        Klietka<Mys> klietkaNaMys = new Klietka<Mys>();
        System.out.println(klietkaNaMys);
        klietkaNaMys.vloz(new Mys());
        System.out.println(klietkaNaMys);
    }
    
}
