/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme;

import domen.Mesto;
import domen.Nalog;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import kontroler.KontrolerKI;
import prenos.Odgovor;

/**
 *
 * @author Jaša
 */
public class NalogForma extends javax.swing.JDialog {

    Nalog korisnik;

    /**
     * Creates new form NalogForma
     */
    public NalogForma(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        popuni();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jtxtIme = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtxtTelefon = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jtxtLozinka = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jtxtLozinkaPonovo = new javax.swing.JPasswordField();
        jComboMesta = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jbtnRegistracija = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("[Dodađaji ] Nalog");

        jLabel1.setText("Ime:");

        jLabel2.setText("Telefon:");

        jLabel3.setText("Lozinka:");

        jLabel4.setText("Ponovite lozinku:");

        jComboMesta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboMestaActionPerformed(evt);
            }
        });

        jLabel5.setText("Mesto:");

        jbtnRegistracija.setText("Registruj se!");
        jbtnRegistracija.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnRegistracijaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jbtnRegistracija)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4))
                            .addGap(27, 27, 27)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jtxtIme)
                                .addComponent(jtxtLozinka)
                                .addComponent(jtxtLozinkaPonovo, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel5))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jComboMesta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jtxtTelefon, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)))))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtxtIme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtxtLozinka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtxtLozinkaPonovo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboMesta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtxtTelefon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(jbtnRegistracija)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboMestaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboMestaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboMestaActionPerformed

    private void jbtnRegistracijaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnRegistracijaActionPerformed
        if (jbtnRegistracija.getText().equalsIgnoreCase("Registruj se!")) {
            if (!validiraj()) {
                return;
            }
            String ime = jtxtIme.getText().trim();
            String lozinka = jtxtLozinka.getText().trim();
            String telefon = jtxtTelefon.getText().trim();
            Mesto m = (Mesto) jComboMesta.getSelectedItem();
            Nalog n = new Nalog(ime, lozinka, telefon, m);
            try {
                Odgovor o = KontrolerKI.getInstance().sacuvajNalog(n);
                JOptionPane.showMessageDialog(rootPane, o.getPoruka(), "Uspešno!", JOptionPane.INFORMATION_MESSAGE);
                jbtnRegistracija.setEnabled(false);
                return;
            } catch (Exception ex) {
                Logger.getLogger(NalogForma.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (jbtnRegistracija.getText().equalsIgnoreCase("Obriši!")) {
            try {
                Odgovor o = KontrolerKI.getInstance().obrisiNalog();
                JOptionPane.showMessageDialog(rootPane, o.getPoruka(), "Uspešno!", JOptionPane.INFORMATION_MESSAGE);
                KontrolerKI.getInstance().odjava();
            } catch (Exception ex) {
                Logger.getLogger(NalogForma.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_jbtnRegistracijaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NalogForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NalogForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NalogForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NalogForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NalogForma dialog = new NalogForma(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    void postaviNalog(Nalog nalogKorisnika) {
        korisnik = nalogKorisnika;
        jtxtIme.setText(nalogKorisnika.getIme());
        jtxtTelefon.setText(nalogKorisnika.getTelefon());
        System.out.println(nalogKorisnika.getMesto().getNaziv());
        jComboMesta.setSelectedItem(nalogKorisnika.getMesto());
        jbtnRegistracija.setText("Obriši!");

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Mesto> jComboMesta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JButton jbtnRegistracija;
    private javax.swing.JTextField jtxtIme;
    private javax.swing.JPasswordField jtxtLozinka;
    private javax.swing.JPasswordField jtxtLozinkaPonovo;
    private javax.swing.JTextField jtxtTelefon;
    // End of variables declaration//GEN-END:variables

    private boolean validiraj() {
        if (!(jtxtLozinka.getText().equals(jtxtLozinkaPonovo.getText()))) {
            JOptionPane.showMessageDialog(rootPane, "Lozinke se ne poklapaju!", "Greška", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (jtxtIme.getText().isEmpty() || jtxtTelefon.getText().isEmpty() || jtxtLozinka.getText().isEmpty() || jtxtLozinkaPonovo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Sva polja moraju biti uneta!", "Greška", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (jtxtIme.getText().contains(" ") || jtxtLozinka.getText().contains(" ")) {
            JOptionPane.showMessageDialog(rootPane, "Polja ne smeju sadržati razmak!", "Greška", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;

    }

    private void popuni() {
        for (Mesto m : KontrolerKI.getInstance().getMesta()) {
            jComboMesta.addItem(m);
        }
    }
}