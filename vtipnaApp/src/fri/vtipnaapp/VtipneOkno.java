/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.vtipnaapp;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
        this.okno.setLayout(new BorderLayout(10, 10));
        this.okno.add(new JLabel("Chceš dostať A zo skúšky?"), BorderLayout.NORTH);
        JPanel tlacidla = new JPanel();
        tlacidla.setLayout(new GridLayout());
        tlacidla.add(new JButton("áno"));
        final JButton nie = new JButton("nie");
        nie.addActionListener(new KlikanieNaNie());
        tlacidla.add(nie);
        this.okno.add(tlacidla, BorderLayout.CENTER);
        this.okno.pack();
    }
    

    void zobraz() {
        this.okno.setVisible(true);
    }
    
}
