package fri.vtipnaapp;

import javax.swing.UIManager;

public class VtipnaApp {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        VtipneOkno okno = new VtipneOkno();
        okno.zobraz();
    }
    
}
