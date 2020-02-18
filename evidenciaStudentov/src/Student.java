/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author janik
 */
public class Student {

    private final String osobneCislo;
    private final String meno;
    private final String priezvisko;
    private Skupina skupina;

    public Student(String osobneCislo, String meno, String priezvisko) {
        this.osobneCislo = osobneCislo;
        this.meno = meno;
        this.priezvisko = priezvisko;
        this.skupina = null;
    }

    public String getOsobneCislo() {
        return this.osobneCislo;
    }

    public String getMeno() {
        return this.meno;
    }

    public String getPriezvisko() {
        return this.priezvisko;
    }

    @Override
    public String toString() {
        return "Student{" + "osobneCislo=" + this.osobneCislo + ", meno=" + this.meno + ", priezvisko=" + this.priezvisko + '}';
    }

    String getCisloSkupiny() {
        if (this.skupina != null) {
            return this.skupina.getCislo();
        } else {
            return null;
        }
    }

    void nastavSkupinu(Skupina skupina) {
        if (this.skupina != null) {
            this.skupina.odstranStudenta(this);
        }
        this.skupina = skupina;
    }
}
