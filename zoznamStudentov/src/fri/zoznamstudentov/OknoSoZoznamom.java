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
public class OknoSoZoznamom extends javax.swing.JFrame {

    /**
     * Creates new form OknoSoZoznamom
     */
    public OknoSoZoznamom() {
        this.initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        lstZoznamStudentov = new javax.swing.JList<>();
        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        txtMeno = new javax.swing.JTextField();
        txtPriezvisko = new javax.swing.JTextField();
        btnVloz = new javax.swing.JButton();
        btnOprav = new javax.swing.JButton();
        btnVymaz = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(1, 2));

        lstZoznamStudentov.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(lstZoznamStudentov);

        getContentPane().add(jScrollPane1);

        jPanel1.setLayout(new java.awt.GridLayout(0, 1));
        jPanel1.add(txtMeno);
        jPanel1.add(txtPriezvisko);

        btnVloz.setText("Vlož");
        jPanel1.add(btnVloz);

        btnOprav.setText("Oprav");
        jPanel1.add(btnOprav);

        btnVymaz.setText("Vymaž");
        jPanel1.add(btnVymaz);

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOprav;
    private javax.swing.JButton btnVloz;
    private javax.swing.JButton btnVymaz;
    private javax.swing.JList<String> lstZoznamStudentov;
    private javax.swing.JTextField txtMeno;
    private javax.swing.JTextField txtPriezvisko;
    // End of variables declaration//GEN-END:variables
}
