/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.vtipnaapp;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 *
 * @author janik
 */
class VtipneOkno {
    private final JFrame okno;
    private final JButton tlacitko1;
    private final JButton tlacitko2;

    public VtipneOkno() {
        this.okno = new JFrame("Otázka");
        this.okno.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.okno.setLayout(new BorderLayout(10, 10));
        this.okno.add(new JLabel("Chceš dostať A zo skúšky?"), BorderLayout.NORTH);
        JPanel tlacidla = new JPanel();
        tlacidla.setLayout(new GridLayout());
        this.tlacitko1 = this.vytvorTlacitko("áno", 1);
        tlacidla.add(this.tlacitko1);
        this.tlacitko2 = this.vytvorTlacitko("nie", 2);
        tlacidla.add(this.tlacitko2);
        
        this.okno.add(tlacidla, BorderLayout.CENTER);
        this.okno.pack();
    }
    
    private JButton vytvorTlacitko(String text, int cisloNieTlacitka) {
        JButton tlacitko = new JButton(text);
        
        tlacitko.addActionListener((ActionEvent ae) -> {
            JOptionPane.showMessageDialog(null, "To som si teda o tebe nemyslel. :(");
            System.exit(0);
        });
        
        tlacitko.addMouseListener(
            new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent me) {
                    switch (cisloNieTlacitka) {
                        case 1:
                            VtipneOkno.this.tlacitko1.setText("nie");
                            VtipneOkno.this.tlacitko2.setText("áno");
                            break;
                        case 2:
                            VtipneOkno.this.tlacitko1.setText("áno");
                            VtipneOkno.this.tlacitko2.setText("nie");
                            break;
                        default:
                            throw new AssertionError();
                    }
                }
            }
        );
        return tlacitko;
    }

    void zobraz() {
        this.okno.setVisible(true);
    }
    
}
