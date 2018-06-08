package com.cecilsoftwares.reussoftfrontend.dialog;

import com.cecilsoftwares.reussoftbackend.service.CategorieProduitService;
import com.cecilsoftwares.reussoftfrontend.form.RegistreCategorieProduit;
import com.cecilsoftwares.reussoftfrontend.form.RegistreProduit;
import com.cecilsoftwares.reussoftmiddleend.model.CategorieProduit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.table.DefaultTableModel;

/**
 * @author Plamedi L. Lusembo
 */
public class ConsultationCategorieProduit extends javax.swing.JDialog {

    private JInternalFrame frameAncetre;
    private CategorieProduit categorieProduit;
    private List<CategorieProduit> categoriesProduits;
    private final DefaultTableModel defaultTableModel;
    private final Object dataRows[];

    /**
     * @param parent
     * @param modal
     */
    public ConsultationCategorieProduit(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        enFermantDialog();

        defaultTableModel = (DefaultTableModel) tblCategorieProduit.getModel();
        dataRows = new Object[3];

        chargementCategoriesProduit();
    }

    private void enFermantDialog() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (frameAncetre instanceof RegistreCategorieProduit) {
                    RegistreCategorieProduit registreCategorieProduit = (RegistreCategorieProduit) frameAncetre;
                    registreCategorieProduit.categorieProduitSelectionne(categorieProduit);
                } else if (frameAncetre instanceof RegistreProduit) {
                    RegistreProduit registreProduit = (RegistreProduit) frameAncetre;
                    registreProduit.categorieProduitSelectionne(categorieProduit);
                }
            }
        });
    }

    private void chargementCategoriesProduit() {
        try {
            categoriesProduits = CategorieProduitService.getInstance().listerTousLesCategorieProduits();
            listerCategoriesProduit(categoriesProduits);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConsultationCategorieProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listerCategoriesProduit(List<CategorieProduit> categoriesProduits) {
        defaultTableModel.setRowCount(0);
        categoriesProduits.forEach(cp -> {
            dataRows[0] = cp.getCode();
            dataRows[1] = cp.getDescription();
            dataRows[2] = cp.getDescriptionAbregee();
            defaultTableModel.addRow(dataRows);
        });

        String formeNombre = categoriesProduits.size() > 1 ? "CategorieProduits" : "CategorieProduit";
        lblNombreCategorieProduit.setText(categoriesProduits.size() + " " + formeNombre);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tfdRechercheDescriptionCategorieProduit = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCategorieProduit = new javax.swing.JTable();
        lblNombreCategorieProduit = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Categories de produit");
        setResizable(false);

        tfdRechercheDescriptionCategorieProduit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfdRechercheDescriptionCategorieProduitKeyReleased(evt);
            }
        });

        tblCategorieProduit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "code", "Description", "Description Abregée"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCategorieProduit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCategorieProduitMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCategorieProduit);
        if (tblCategorieProduit.getColumnModel().getColumnCount() > 0) {
            tblCategorieProduit.getColumnModel().getColumn(0).setResizable(false);
            tblCategorieProduit.getColumnModel().getColumn(1).setResizable(false);
            tblCategorieProduit.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblCategorieProduit.getColumnModel().getColumn(2).setResizable(false);
            tblCategorieProduit.getColumnModel().getColumn(2).setPreferredWidth(150);
        }

        lblNombreCategorieProduit.setText("Chargement...");

        jLabel1.setText("Description:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(lblNombreCategorieProduit)
                    .addComponent(tfdRechercheDescriptionCategorieProduit, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfdRechercheDescriptionCategorieProduit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNombreCategorieProduit)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tblCategorieProduitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCategorieProduitMouseClicked
        if (evt.getClickCount() == 2) {
            if (frameAncetre != null) {
                int row = tblCategorieProduit.getSelectedRow();

                categorieProduit = categoriesProduits.stream()
                        .filter(cp -> cp.getCode() == (int) defaultTableModel.getValueAt(row, 0))
                        .findFirst().orElse(null);
            }
            dispose();
        }

    }//GEN-LAST:event_tblCategorieProduitMouseClicked

    private void tfdRechercheDescriptionCategorieProduitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdRechercheDescriptionCategorieProduitKeyReleased
        List<CategorieProduit> listeCategorieProduits = new ArrayList();

        categoriesProduits.stream().filter((cp) -> (cp.getDescription().toUpperCase()
                .startsWith(tfdRechercheDescriptionCategorieProduit.getText().toUpperCase())))
                .forEachOrdered((cp) -> {
                    listeCategorieProduits.add(cp);
                });

        listerCategoriesProduit(listeCategorieProduits);
    }//GEN-LAST:event_tfdRechercheDescriptionCategorieProduitKeyReleased

    public JInternalFrame getFrameAncetre() {
        return frameAncetre;
    }

    public void setFrameAncetre(JInternalFrame frameAncetre) {
        this.frameAncetre = frameAncetre;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblNombreCategorieProduit;
    private javax.swing.JTable tblCategorieProduit;
    private javax.swing.JTextField tfdRechercheDescriptionCategorieProduit;
    // End of variables declaration//GEN-END:variables
}
