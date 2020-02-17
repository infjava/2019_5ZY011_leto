/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author janik
 */
public class EvidenciaStudentov {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Student novy = new Student("1001120", "Jan", "Janech");
        Skupina skupina = new Skupina("5ZY011");
        skupina.pridajStudenta(novy);
        skupina.vypisStudentov();
    }
    
}
