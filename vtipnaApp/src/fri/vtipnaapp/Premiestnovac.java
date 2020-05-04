/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fri.vtipnaapp;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author janik
 */
public class Premiestnovac extends MouseAdapter {

    private final JButton tlacitko1;
    private final JButton tlacitko2;
    private final int nieTlacitko;

    Premiestnovac(JButton tlacitko1, JButton tlacitko2, int nieTlacitko) {
        this.tlacitko1 = tlacitko1;
        this.tlacitko2 = tlacitko2;
        this.nieTlacitko = nieTlacitko;
        
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        switch (this.nieTlacitko) {
            case 1:
                this.tlacitko1.setText("nie");
                this.tlacitko2.setText("áno");
                break;
            case 2:
                this.tlacitko1.setText("áno");
                this.tlacitko2.setText("nie");
                break;
            default:
                throw new AssertionError();
        }
    }
    
}
