package com.cecilsoftwares.reussoftfrontend.form;

import com.cecilsoftwares.reussoftbackend.service.CategorieProduitService;
import com.cecilsoftwares.reussoftfrontend.dialog.ConsultationCategorieProduit;
import com.cecilsoftwares.reussoftmiddleend.model.CategorieProduit;
import com.cecilsoftwares.reussoftmiddleend.model.CategorieProduit.CategorieProduitBuilder;
import java.awt.Cursor;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 * @author Plamedi L. Lusembo
 */
public class RegistreCategorieProduit extends JInternalFrame {

    private int codeCategorieProduit;
    private boolean modeEdition;
    private boolean btnConsulterCategorieProduitClickable;
    private boolean btnEnregistrerClickable;
    private boolean btnExclureClickable;
    private boolean btnAnnulerClickable;

    public RegistreCategorieProduit() {
        initComponents();
        effacerFormulaire();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tfdDescription = new javax.swing.JTextField();
        tfdDescriptionAbregee = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaObservation = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnAnnuler = new javax.swing.JButton();
        btnEnregistrer = new javax.swing.JButton();
        btnConsulterCategorieProduit = new javax.swing.JButton();
        btnExclure = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registre de Catégorie de Produit");

        txaObservation.setColumns(20);
        txaObservation.setRows(5);
        jScrollPane1.setViewportView(txaObservation);

        jLabel2.setText("Description:");

        jLabel3.setText("Description abregrée:");

        jLabel4.setText("Observation:");

        btnAnnuler.setText("ANNULER");
        btnAnnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnnulerActionPerformed(evt);
            }
        });

        btnEnregistrer.setText("ENREGISTRER");
        btnEnregistrer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnregistrerActionPerformed(evt);
            }
        });

        btnConsulterCategorieProduit.setText("...");
        btnConsulterCategorieProduit.setFocusable(false);
        btnConsulterCategorieProduit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsulterCategorieProduitActionPerformed(evt);
            }
        });

        btnExclure.setText("EXCLURE");
        btnExclure.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExclureActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(tfdDescriptionAbregee, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnEnregistrer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnExclure, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(tfdDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnConsulterCategorieProduit))))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsulterCategorieProduit))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdDescriptionAbregee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEnregistrer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExclure, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnnulerActionPerformed
        effacerFormulaire();
    }//GEN-LAST:event_btnAnnulerActionPerformed

    private void btnEnregistrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnregistrerActionPerformed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        habiliterComposantFormulaire(false);

        CategorieProduit categorieProduit = new CategorieProduitBuilder(codeCategorieProduit)
                .description(tfdDescription.getText())
                .descriptionAbregee(tfdDescriptionAbregee.getText())
                .observation(txaObservation.getText())
                .build();

        try {
            if (CategorieProduitService.getInstance().enregistrerCategorieProduit(categorieProduit)) {
                String notification = modeEdition ? "Actualisation effectuée avec succès" : "Sauvegarde effectuée avec succès";
                effacerFormulaire();
                JOptionPane.showMessageDialog(null, notification);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Une faille est survenue en sauvegardant la Catégorie Produit");
            Logger.getLogger(RegistreShop.class.getName()).log(Level.SEVERE, null, ex);
        }

        setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnEnregistrerActionPerformed

    private void btnConsulterCategorieProduitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsulterCategorieProduitActionPerformed
        if (!btnConsulterCategorieProduitClickable) {
            return;
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        habiliterComposantFormulaire(false);

        consulterCategorieProduit();

        habiliterComposantFormulaire(true);
        setCursor(Cursor.getDefaultCursor());

    }//GEN-LAST:event_btnConsulterCategorieProduitActionPerformed

    private void btnExclureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExclureActionPerformed
        Object[] options = {"Exlure", "Annuler"};
        int n = JOptionPane.showOptionDialog(this,
                "Would you like green eggs and ham?",
                "A Silly Question",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, //do not use a custom Icon
                options, //the titles of buttons
                options[0]); //default button title

        if (n == 1) {
            try {
                CategorieProduitService.getInstance().exclureCategorieProduit(codeCategorieProduit);
                String notification = "Exclusion effectuée avec succès";
                effacerFormulaire();
                JOptionPane.showMessageDialog(null, notification);
            } catch (SQLException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Une faille est survenue lors de l'exclusion de la Catégorie Produit");
                Logger.getLogger(RegistreShop.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnExclureActionPerformed

    private void consulterCategorieProduit() {
        ConsultationCategorieProduit consultationCategorieProduit = new ConsultationCategorieProduit(null, true);
        consultationCategorieProduit.setFrameAncetre(this);
        consultationCategorieProduit.setVisible(true);
    }

    public void categorieProduitSelectionne(CategorieProduit categorieProduit) {
        if (categorieProduit == null) {
            return;
        }

        modeEdition = true;
        codeCategorieProduit = categorieProduit.getCode();
        tfdDescription.setText(categorieProduit.getDescription());
        tfdDescriptionAbregee.setText(categorieProduit.getDescriptionAbregee());
        txaObservation.setText(categorieProduit.getObservation());
        btnEnregistrer.setText("ACTUALISER");
    }

    private void effacerFormulaire() {
        codeCategorieProduit = 0;
        tfdDescription.setText("");
        tfdDescriptionAbregee.setText("");
        txaObservation.setText("");
        btnEnregistrer.setText("ENREGISTRER");
        modeEdition = false;
        habiliterComposantFormulaire(true);
    }

    private void habiliterComposantFormulaire(boolean hcf) {
        tfdDescription.setEditable(hcf);
        tfdDescriptionAbregee.setEditable(hcf);
        txaObservation.setEditable(hcf);
        btnConsulterCategorieProduitClickable = hcf;
        btnEnregistrerClickable = hcf;
        btnAnnulerClickable = hcf;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnnuler;
    private javax.swing.JButton btnConsulterCategorieProduit;
    private javax.swing.JButton btnEnregistrer;
    private javax.swing.JButton btnExclure;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField tfdDescription;
    private javax.swing.JTextField tfdDescriptionAbregee;
    private javax.swing.JTextArea txaObservation;
    // End of variables declaration//GEN-END:variables
}
