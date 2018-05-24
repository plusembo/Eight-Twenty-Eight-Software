package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.Shop;
import com.cecilsoftwares.reussoftmiddleend.model.Shop.ShopBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.TauxCarte;
import com.cecilsoftwares.reussoftmiddleend.model.TauxCarte.TauxCarteBuilder;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class TauxCarteDao {

    private StringBuilder scriptSQL;
    private static TauxCarteDao uniqueInstance;

    public TauxCarteDao() {
    }

    public static synchronized TauxCarteDao getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new TauxCarteDao();
        }
        return uniqueInstance;
    }

    public List<TauxCarte> listerTousLesTauxCartes() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<TauxCarte> listeTauxCartes;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            listeTauxCartes = new ArrayList();

            scriptSQL = new StringBuilder("SELECT tauxcartes.code, tauxcartes.dateheure, tauxcartes.valeur, tauxcartes.observation,");
            scriptSQL.append(" tauxcartes.idShop, shop.nom, shop.adresse");
            scriptSQL.append(" FROM tauxcartes");
            scriptSQL.append(" LEFT JOIN shop ON tauxcartes.idShop = shop.code");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    Shop shop = new ShopBuilder(res.getInt(11))
                            .nom(res.getString(12))
                            .adresse(res.getString(13))
                            .build();

                    TauxCarte tauxCarte = new TauxCarteBuilder(res.getInt(1))
                            .dateHeure(res.getTimestamp(2))
                            .valeur(new BigDecimal(res.getString(3)))
                            .observation(res.getString(4))
                            .shop(shop)
                            .build();

                    listeTauxCartes.add(tauxCarte);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeTauxCartes;
    }

    public TauxCarte selectionnerTauxCarteParCode(int codeTauxCarte) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("SELECT tauxcartes.code, tauxcartes.dateheure, tauxcartes.valeur, tauxcartes.observation,");
            scriptSQL.append(" tauxcartes.idShop, shop.nom, shop.adresse");
            scriptSQL.append(" FROM tauxcartes");
            scriptSQL.append(" LEFT JOIN shop ON tauxcartes.idShop = shop.code");
            scriptSQL.append(" WHERE tauxcartes.code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeTauxCarte);
            res = prs.executeQuery();
            if (res != null) {
                if (res.next()) {

                    Shop shop = new ShopBuilder(res.getInt(11))
                            .nom(res.getString(12))
                            .adresse(res.getString(13))
                            .build();

                    TauxCarte tauxCarte = new TauxCarteBuilder(res.getInt(1))
                            .dateHeure(res.getTimestamp(2))
                            .valeur(new BigDecimal(res.getString(3)))
                            .observation(res.getString(4))
                            .shop(shop)
                            .build();

                    return tauxCarte;
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return null;
    }

    public boolean sauvegarderTauxCarte(TauxCarte tauxCarte) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("INSERT INTO tauxcarte(");
            scriptSQL.append(" code, dateheure, valeur, observateur )");
            scriptSQL.append(" VALUES (?, ?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, tauxCarte.getCode());
            prs.setTimestamp(2, tauxCarte.getDateHeure());
            prs.setBigDecimal(3, tauxCarte.getValeur());
            prs.setString(4, tauxCarte.getObservation());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    public boolean actualiserTauxCarte(TauxCarte tauxCarte) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("UPDATE sessionutilisateur");
            scriptSQL.append(" SET dateheure=?, valeur=?, observateur=?,");
            scriptSQL.append(" WHERE code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setTimestamp(1, tauxCarte.getDateHeure());
            prs.setBigDecimal(2, tauxCarte.getValeur());
            prs.setString(3, tauxCarte.getObservation());
            prs.setInt(4, tauxCarte.getCode());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }
}
