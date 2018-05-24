package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.ProfilUtilisateur;
import com.cecilsoftwares.reussoftmiddleend.model.ProfilUtilisateur.ProfilUtilisateurBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class ProfilUtilisateurDao {

    private StringBuilder scriptSQL;
    private static ProfilUtilisateurDao uniqueInstance;

    public ProfilUtilisateurDao() {
    }

    public static synchronized ProfilUtilisateurDao getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ProfilUtilisateurDao();
        }
        return uniqueInstance;
    }

    //valide = true
    public List<ProfilUtilisateur> listerTousLesProfilUtilisateurs()
            throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<ProfilUtilisateur> profilUtilisateurs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            profilUtilisateurs = new ArrayList();

            scriptSQL = new StringBuilder("SELECT code, description, descriptionAbregee, observation");
            scriptSQL.append(" FROM profilutilisateur");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    ProfilUtilisateur profilUtilisateur = new ProfilUtilisateurBuilder(res.getInt(1))
                            .description(res.getString(2))
                            .descriptionAbregee(res.getString(3))
                            .observation(res.getString(4))
                            .build();

                    profilUtilisateurs.add(profilUtilisateur);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return profilUtilisateurs;
    }

    //valide = true
    public ProfilUtilisateur selectionnerProfilUtilisateurParCode(int codeProfilUtilisateur)
            throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("SELECT code, description, descriptionAbregee, observation");
            scriptSQL.append(" FROM profilutilisateur WHERE code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeProfilUtilisateur);
            res = prs.executeQuery();
            if (res != null) {
                if (res.next()) {

                    ProfilUtilisateur profilUtilisateur = new ProfilUtilisateurBuilder(res.getInt(1))
                            .description(res.getString(2))
                            .descriptionAbregee(res.getString(3))
                            .observation(res.getString(4))
                            .build();

                    return profilUtilisateur;
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return null;
    }

    //valide = true
    public boolean enregistrerProfilUtilisateur(ProfilUtilisateur profilUtilisateur) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("INSERT INTO profilutilisateur(");
            scriptSQL.append(" code, description, descriptionAbregee, observation");
            scriptSQL.append(" VALUES (?, ?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, profilUtilisateur.getCode());
            prs.setString(2, profilUtilisateur.getDescription());
            prs.setString(3, profilUtilisateur.getDescriptionAbregee());
            prs.setString(4, profilUtilisateur.getObservation());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    //valide = true
    public boolean actualiserProfilUtilisateur(ProfilUtilisateur profilUtilisateur) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("UPDATE profilutilisateur");
            scriptSQL.append(" SET description=?, descriptionAbregee=?, observation=?");
            scriptSQL.append(" WHERE code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setString(1, profilUtilisateur.getDescription());
            prs.setString(2, profilUtilisateur.getDescriptionAbregee());
            prs.setString(3, profilUtilisateur.getObservation());
            prs.setInt(4, profilUtilisateur.getCode());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    public int selectionnerCodeProfilUtilisateurSubsequent() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT Max(code)+1 FROM profilutilisateur");
            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                if (res.next()) {
                    int cdSubsequente = res.getInt(1);

                    prs.close();
                    res.close();
                    conexao.close();

                    return cdSubsequente;
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return 0;
    }
}
