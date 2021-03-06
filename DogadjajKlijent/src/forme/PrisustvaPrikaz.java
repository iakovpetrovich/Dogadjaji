/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme;

import domen.Prisustvo;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import kontroler.KontrolerKI;
import modelTabela.TableModelPrisustva;
import prenos.Odgovor;

/**
 *
 * @author Jaša
 */
public class PrisustvaPrikaz extends javax.swing.JDialog {

    TableModelPrisustva tmp = new TableModelPrisustva();
    /**
     * Creates new form PrisustvaPrikaz
     */
    public PrisustvaPrikaz(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        try {
            postaviModel(kontroler.KontrolerKI.getInstance().pronadjiMojaPrisustva());
            JOptionPane.showMessageDialog(this, KontrolerKI.getInstance().getOdgovor().getPoruka(), "Uspešno!", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(PrisustvaPrikaz.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Greška!", JOptionPane.WARNING_MESSAGE);
            dispose();
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePrisustva = new javax.swing.JTable();
        jbtnPogledaj = new javax.swing.JButton();
        jbtnObtisi = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("[Dodađaji ] Prikaz prisustva");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Vaša prisustva:"));

        jTablePrisustva.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTablePrisustva);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81))
        );

        jbtnPogledaj.setText("Prikaži");
        jbtnPogledaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnPogledajActionPerformed(evt);
            }
        });

        jbtnObtisi.setText("Obriši");
        jbtnObtisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnObtisiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbtnPogledaj, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtnObtisi, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnPogledaj)
                    .addComponent(jbtnObtisi))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnObtisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnObtisiActionPerformed
        try {
            Odgovor o = KontrolerKI.getInstance().obrisiPrisustvo(tmp.getListaPrisustvoi().get(jTablePrisustva.getSelectedRow()));
            JOptionPane.showMessageDialog(this, "Sistem je obrisao prisustvo.", "Uspešno!", JOptionPane.INFORMATION_MESSAGE);
            tmp.promeniStanje(KontrolerKI.getInstance().pronadjiMojaPrisustva());  
        } catch (Exception ex) {
            Logger.getLogger(PrisustvaPrikaz.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Greška!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jbtnObtisiActionPerformed

    private void jbtnPogledajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnPogledajActionPerformed
        try {
            Odgovor o = KontrolerKI.getInstance().prikaziPrisustvo(tmp.getListaPrisustvoi().get(jTablePrisustva.getSelectedRow()));
            JOptionPane.showMessageDialog(this, o.getPoruka(), "Uspešno!", JOptionPane.INFORMATION_MESSAGE);
            PrisustvoForma pf = new PrisustvoForma(null, true);
            pf.sredi((Prisustvo)o.getRezultat());
            pf.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(PrisustvaPrikaz.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Greška!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jbtnPogledajActionPerformed

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
            java.util.logging.Logger.getLogger(PrisustvaPrikaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrisustvaPrikaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrisustvaPrikaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrisustvaPrikaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PrisustvaPrikaz dialog = new PrisustvaPrikaz(new javax.swing.JFrame(), true);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablePrisustva;
    private javax.swing.JButton jbtnObtisi;
    private javax.swing.JButton jbtnPogledaj;
    // End of variables declaration//GEN-END:variables



    private void postaviModel(LinkedList<Prisustvo> prisustva) {
        tmp = new TableModelPrisustva(prisustva);
        jTablePrisustva.setModel(tmp);
    }
}
