package com.cecilsoftwares.reussoftfrontend.form;

import com.cecilsoftwares.reussoftbackend.service.ProfilUtilisateurService;
import com.cecilsoftwares.reussoftfrontend.dialog.ConsultationProfilUtilisateur;
import com.cecilsoftwares.reussoftmiddleend.model.ProfilUtilisateur;
import java.awt.Cursor;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 * @author Plamedi L. Lusembo
 */
public class RegistreProfilUtilisateur extends JInternalFrame {

    private int codeProfilUtilisateur;
    private boolean modeEdition;
    private boolean btnConsulterProfilUtilisateurClickable;
    private boolean btnEnregistrerClickable;
    private boolean btnExclureClickable;
    private boolean btnAnnulerClickable;

    public RegistreProfilUtilisateur() {
        initComponents();
        effacerFormulaire();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tfdDescription = new javax.swing.JTextField();
        tfdDescriptionAbregee = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnAnnuler = new javax.swing.JButton();
        btnEnregistrer = new javax.swing.JButton();
        btnExclure = new javax.swing.JButton();
        btnConsulterProfilUtilisateur = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registre de Profil d'utilisateur");

        jLabel2.setText("Description:");

        jLabel3.setText("Description abregrée:");

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

        btnExclure.setText("EXCLURE");
        btnExclure.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExclureActionPerformed(evt);
            }
        });

        btnConsulterProfilUtilisateur.setText("...");
        btnConsulterProfilUtilisateur.setFocusable(false);
        btnConsulterProfilUtilisateur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsulterProfilUtilisateurActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)
                        .addComponent(tfdDescriptionAbregee, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(tfdDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnConsulterProfilUtilisateur)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEnregistrer, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExclure, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfdDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsulterProfilUtilisateur))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdDescriptionAbregee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEnregistrer, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExclure, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnnulerActionPerformed
        effacerFormulaire();
    }//GEN-LAST:event_btnAnnulerActionPerformed

    private void btnEnregistrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnregistrerActionPerformed

        if (isInformationObligatoiresRemplies()) {

            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            habiliterComposantFormulaire(false);

            ProfilUtilisateur profilUtilisateur = new ProfilUtilisateur(codeProfilUtilisateur);
            profilUtilisateur.setDescription(tfdDescription.getText());
            profilUtilisateur.setDescriptionAbregee(tfdDescriptionAbregee.getText());

            try {
                if (ProfilUtilisateurService.getInstance().enregistrerProfilUtilisateur(profilUtilisateur)) {
                    String notification = modeEdition ? "Actualisation effectuée avec succès" : "Sauvegarde effectuée avec succès";
                    effacerFormulaire();
                    JOptionPane.showMessageDialog(null, notification);
                }
            } catch (ClassNotFoundException | SQLException ex) {
                JOptionPane.showMessageDialog(null, "Une faille est survenue en sauvegardant la Catégorie Produit");
                Logger.getLogger(RegistreShop.class.getName()).log(Level.SEVERE, null, ex);
            }
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnEnregistrerActionPerformed

    private void btnConsulterProfilUtilisateurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsulterProfilUtilisateurActionPerformed
        if (btnConsulterProfilUtilisateurClickable) {

            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            habiliterComposantFormulaire(false);
            try {
                ConsultationProfilUtilisateur consultationProfilUtilisateur = new ConsultationProfilUtilisateur(null, true, ProfilUtilisateurService.getInstance()
                        .listerTousLesProfilUtilisateurs());
                consultationProfilUtilisateur.setFrameAncetre(this);
                consultationProfilUtilisateur.setVisible(true);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(RegistreProfilUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
            }

            habiliterComposantFormulaire(true);
            setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_btnConsulterProfilUtilisateurActionPerformed

    private void btnExclureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExclureActionPerformed
        Object[] options = {"Exclure", "Annuler"};
        int n = JOptionPane.showOptionDialog(this,
                "Êtes-vous sûr de vouloir exclure définitivement ce profil d'utilisateur?",
                "Question",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, //do not use a custom Icon
                options, //the titles of buttons
                options[0]); //default button title

        if (n == 0) {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            habiliterComposantFormulaire(false);
            try {
                ProfilUtilisateurService.getInstance().exclureProfilUtilisateur(codeProfilUtilisateur);
                effacerFormulaire();
                JOptionPane.showMessageDialog(null, "Exclusion effectuée avec succès");
            } catch (SQLException ex) {
                StringBuilder notification = new StringBuilder("Une faille est survenue lors de l'exclusion du profil d'utilisateur :(");
                switch (ex.getErrorCode()) {
                    case 1451:
                        notification.append("\n\nCe profil d'utilisateur est utilisé par un autre registre!");
                        break;
                    default:
                        break;
                }
                JOptionPane.showMessageDialog(null, notification);
                habiliterComposantFormulaire(true);
                Logger.getLogger(RegistreShop.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Une faille est survenue lors de l'exclusion du profil d'utilisateur :(");
                habiliterComposantFormulaire(true);
            } finally {
                setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_btnExclureActionPerformed

    public void profilUtilisateurSelectionne(ProfilUtilisateur profilUtilisateur) {
        if (profilUtilisateur != null) {

            modeEdition = true;
            btnExclure.setEnabled(true);

            codeProfilUtilisateur = profilUtilisateur.getCode();
            tfdDescription.setText(profilUtilisateur.getDescription());
            tfdDescriptionAbregee.setText(profilUtilisateur.getDescriptionAbregee());
            btnEnregistrer.setText("ACTUALISER");
        }
    }

    private void effacerFormulaire() {
        codeProfilUtilisateur = 0;
        tfdDescription.setText("");
        tfdDescription.requestFocus();
        tfdDescriptionAbregee.setText("");
        modeEdition = false;
        btnEnregistrer.setText("ENREGISTRER");
        habiliterComposantFormulaire(true);
        btnExclure.setEnabled(false);

    }

    private void habiliterComposantFormulaire(boolean hcf) {
        tfdDescription.setEditable(hcf);
        tfdDescriptionAbregee.setEditable(hcf);
        btnConsulterProfilUtilisateurClickable = hcf;
        btnEnregistrerClickable = hcf;
        btnExclureClickable = hcf;
        btnAnnulerClickable = hcf;
    }

    private boolean isInformationObligatoiresRemplies() {

        StringBuilder notification = new StringBuilder();
        Queue<Integer> nio = new LinkedList<>();

        if (tfdDescription.getText().isEmpty()) {
            notification.append("\nDescription");
            nio.add(1);
        }
        if (tfdDescriptionAbregee.getText().isEmpty()) {
            notification.append("\nDescription Abrégée");
            nio.add(2);
        }

        if (notification.toString().isEmpty()) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, new StringBuilder(nio.size() > 1 ? "Informations obligatoires:" : "Information obligatoire:")
                    .append(notification));
            switch (nio.poll()) {
                case 1:
                    tfdDescription.requestFocus();
                    break;

                case 2:
                    tfdDescriptionAbregee.requestFocus();
                    break;

                default:
            }
            return false;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnnuler;
    private javax.swing.JButton btnConsulterProfilUtilisateur;
    private javax.swing.JButton btnEnregistrer;
    private javax.swing.JButton btnExclure;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField tfdDescription;
    private javax.swing.JTextField tfdDescriptionAbregee;
    // End of variables declaration//GEN-END:variables
}
