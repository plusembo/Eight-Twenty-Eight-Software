package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.Shop;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class ShopDao {

    private StringBuilder scriptSQL;
    private static ShopDao uniqueInstance;

    public ShopDao() {
    }

    public static synchronized ShopDao getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ShopDao();
        }
        return uniqueInstance;
    }

    public List<Shop> listerTousLesShops() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<Shop> listeShops;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT code, nom, adresse, active");
            scriptSQL.append(" FROM shop");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();

            listeShops = new ArrayList();
            if (res != null) {
                while (res.next()) {

                    Shop shop = new Shop(res.getInt(1));
                    shop.setNom(res.getString(2));
                    shop.setAdresse(res.getString(3));
                    shop.setActive(res.getInt(4) == 1);

                    listeShops.add(shop);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeShops;
    }

    public Shop selectionnerShopParCode(int codeShop) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT code, nom, adresse, active");
            scriptSQL.append(" FROM shop WHERE code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeShop);
            res = prs.executeQuery();

            if (res != null) {
                if (res.next()) {

                    Shop shop = new Shop(res.getInt(1));
                    shop.setNom(res.getString(2));
                    shop.setAdresse(res.getString(3));
                    shop.setActive(res.getInt(4) == 1);

                    prs.close();
                    res.close();
                    conexao.close();

                    return shop;
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return null;
    }

    public boolean enregistrerShop(Shop shop) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            if (shop.getCode() == 0) {

                scriptSQL = new StringBuilder("INSERT INTO shop(");
                scriptSQL.append(" nom, adresse, active, code)");
                scriptSQL.append(" VALUES (?, ?, ?, ?)");
            } else {

                scriptSQL = new StringBuilder("UPDATE shop");
                scriptSQL.append(" SET nom=?, adresse=?, active=?");
                scriptSQL.append(" WHERE code=?");
            }

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setString(1, shop.getNom());
            prs.setString(2, shop.getAdresse());
            prs.setInt(3, shop.isActive() ? 1 : 0);
            prs.setInt(4, shop.getCode());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    public boolean exclureShop(int codeShop) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("DELETE FROM shop WHERE code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeShop);

            prs.execute();
            prs.close();
            conexao.close();
        }

        return true;
    }

}
