/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.zoznamstudentov;

/**
 *
 * @author janik
 */
public class Student {

    private final String meno;
    private final String priezvisko;

    Student(String meno, String priezvisko) {
        this.meno = meno;
        this.priezvisko = priezvisko;
    }

    public String getMeno() {
        return this.meno;
    }

    public String getPriezvisko() {
        return this.priezvisko;
    }

    @Override
    public String toString() {
        return this.meno + " " + this.priezvisko;
    }
    
}
