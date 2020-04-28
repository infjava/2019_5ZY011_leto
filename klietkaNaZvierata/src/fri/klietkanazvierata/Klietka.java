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
class Klietka {

    private Zviera zviera;

    public Klietka() {
        this.zviera = null;
    }

    void vloz(Zviera zviera) {
        this.zviera = zviera;
    }

    @Override
    public String toString() {
        if (this.zviera == null)
            return "Klietka{prazdna}";
        return "Klietka{" + "Obsah:" + this.zviera + '}';
    }
    
}
