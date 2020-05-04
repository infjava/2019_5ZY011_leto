/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.vtipnaapp;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 *
 * @author janik
 */
class VtipneOkno {

    private final JFrame okno;

    public VtipneOkno() {
        this.okno = new JFrame("Otázka");
        this.okno.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.okno.add(new JLabel("Chceš dostať A zo skúšky?"));
        this.okno.pack();
    }
    

    void zobraz() {
        this.okno.setVisible(true);
    }
    
}
