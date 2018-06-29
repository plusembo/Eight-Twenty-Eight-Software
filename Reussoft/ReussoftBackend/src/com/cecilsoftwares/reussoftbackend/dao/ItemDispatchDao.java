package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.ItemDispatch;
import com.cecilsoftwares.reussoftmiddleend.model.Dispatch;
import com.cecilsoftwares.reussoftmiddleend.model.Produit;
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
public class ItemDispatchDao {

    private StringBuilder scriptSQL;
    private static ItemDispatchDao uniqueInstance;

    public ItemDispatchDao() {
    }

    public static synchronized ItemDispatchDao getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ItemDispatchDao();
        }
        return uniqueInstance;
    }

    public List<ItemDispatch> listerTousLesItemsDispatch() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<ItemDispatch> listeItemsDispatch;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            listeItemsDispatch = new ArrayList();

            scriptSQL = new StringBuilder("SELECT itemdispatch.quantite,");
            scriptSQL.append(" itemdispatch.idDispatch, dispatch.dateHeure, dispatch.valide,");
            scriptSQL.append(" dispatch.idShop, shopExpediteur.nom,");
            scriptSQL.append(" itemdispatch.idShop, shopDestinataire.nom,");
            scriptSQL.append(" itemdispatch.idProduto, produit.description");
            scriptSQL.append(" FROM itemdispatch");
            scriptSQL.append(" LEFT JOIN dispatch ON itemdispatch.idDispatch = dispatch.code");
            scriptSQL.append(" LEFT JOIN shop as shopExpediteur ON dispatch.idShop = shopExpediteur.code");
            scriptSQL.append(" LEFT JOIN shop as shopDestinataire ON itemdispatch.idShop = shopDestinataire.code");
            scriptSQL.append(" LEFT JOIN produit ON itemdispatch.idProduit = produit.code");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    Dispatch dispatch = new Dispatch(res.getInt(2));
                    dispatch.setDateHeure(res.getTimestamp(3));
                    dispatch.setValide(res.getInt(4) == 1);

                    Shop shopExpediteur = new Shop(res.getInt(5));
                    shopExpediteur.setNom(res.getString(6));
                    dispatch.setShop(shopExpediteur);

                    Produit produit = new Produit(res.getInt(9));
                    produit.setDescription(res.getString(10));

                    ItemDispatch itemDispatch = new ItemDispatch(dispatch, produit);
                    itemDispatch.setQuantiteProduit(res.getBigDecimal(1));
                    itemDispatch.setQuantiteProduit(res.getBigDecimal(3));

                    Shop shopDestinataire = new Shop(res.getInt(7));
                    shopDestinataire.setNom(res.getString(8));
                    itemDispatch.setShop(shopDestinataire);

                    listeItemsDispatch.add(itemDispatch);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeItemsDispatch;
    }

    public List<ItemDispatch> listerItemsDispatchParCodeDispatch(int codeDispatch) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<ItemDispatch> listeItemsDispatch;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            listeItemsDispatch = new ArrayList();

            scriptSQL = new StringBuilder("SELECT itemdispatch.quantite,");
            scriptSQL.append(" itemdispatch.idDispatch, dispatch.dateHeure, dispatch.valide,");
            scriptSQL.append(" dispatch.idShop, shopExpediteur.nom,");
            scriptSQL.append(" itemdispatch.idShop, shopDestinataire.nom,");
            scriptSQL.append(" itemdispatch.idProduto, produit.description");
            scriptSQL.append(" FROM itemdispatch");
            scriptSQL.append(" LEFT JOIN dispatch ON itemdispatch.idDispatch = dispatch.code");
            scriptSQL.append(" LEFT JOIN shop as shopExpediteur ON dispatch.idShop = shopExpediteur.code");
            scriptSQL.append(" LEFT JOIN shop as shopDestinataire ON itemdispatch.idShop = shopDestinataire.code");
            scriptSQL.append(" LEFT JOIN produit ON itemdispatch.idProduit = produit.code");
            scriptSQL.append(" WHERE dispatch.code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeDispatch);
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    Dispatch dispatch = new Dispatch(res.getInt(2));
                    dispatch.setDateHeure(res.getTimestamp(3));
                    dispatch.setValide(res.getInt(4) == 1);

                    Shop shopExpediteur = new Shop(res.getInt(5));
                    shopExpediteur.setNom(res.getString(6));
                    dispatch.setShop(shopExpediteur);

                    Produit produit = new Produit(res.getInt(9));
                    produit.setDescription(res.getString(10));

                    ItemDispatch itemDispatch = new ItemDispatch(dispatch, produit);
                    itemDispatch.setQuantiteProduit(res.getBigDecimal(1));
                    itemDispatch.setQuantiteProduit(res.getBigDecimal(3));

                    Shop shopDestinataire = new Shop(res.getInt(7));
                    shopDestinataire.setNom(res.getString(8));
                    itemDispatch.setShop(shopDestinataire);

                    listeItemsDispatch.add(itemDispatch);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeItemsDispatch;
    }

    public List<ItemDispatch> listerItemsDispatchParCodeProduit(int codeProduit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<ItemDispatch> listeItemsDispatch;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            listeItemsDispatch = new ArrayList();

            scriptSQL = new StringBuilder("SELECT itemdispatch.quantite,");
            scriptSQL.append(" itemdispatch.idDispatch, dispatch.dateHeure, dispatch.valide,");
            scriptSQL.append(" dispatch.idShop, shopExpediteur.nom,");
            scriptSQL.append(" itemdispatch.idShop, shopDestinataire.nom,");
            scriptSQL.append(" itemdispatch.idProduto, produit.description");
            scriptSQL.append(" FROM itemdispatch");
            scriptSQL.append(" LEFT JOIN dispatch ON itemdispatch.idDispatch = dispatch.code");
            scriptSQL.append(" LEFT JOIN shop as shopExpediteur ON dispatch.idShop = shopExpediteur.code");
            scriptSQL.append(" LEFT JOIN shop as shopDestinataire ON itemdispatch.idShop = shopDestinataire.code");
            scriptSQL.append(" LEFT JOIN produit ON itemdispatch.idProduit = produit.code");
            scriptSQL.append(" WHERE produit.code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeProduit);
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    Dispatch dispatch = new Dispatch(res.getInt(2));
                    dispatch.setDateHeure(res.getTimestamp(3));
                    dispatch.setValide(res.getInt(4) == 1);

                    Shop shopExpediteur = new Shop(res.getInt(5));
                    shopExpediteur.setNom(res.getString(6));
                    dispatch.setShop(shopExpediteur);

                    Produit produit = new Produit(res.getInt(9));
                    produit.setDescription(res.getString(10));

                    ItemDispatch itemDispatch = new ItemDispatch(dispatch, produit);
                    itemDispatch.setQuantiteProduit(res.getBigDecimal(1));
                    itemDispatch.setQuantiteProduit(res.getBigDecimal(3));

                    Shop shopDestinataire = new Shop(res.getInt(7));
                    shopDestinataire.setNom(res.getString(8));
                    itemDispatch.setShop(shopDestinataire);

                    listeItemsDispatch.add(itemDispatch);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeItemsDispatch;
    }

    public ItemDispatch selectionnerItemDispatchParCodeDispatchECodeProduit(int codeDispatch, int codeProduit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("SELECT itemdispatch.quantite,");
            scriptSQL.append(" itemdispatch.idDispatch, dispatch.dateHeure, dispatch.valide,");
            scriptSQL.append(" dispatch.idShop, shopExpediteur.nom,");
            scriptSQL.append(" itemdispatch.idShop, shopDestinataire.nom,");
            scriptSQL.append(" itemdispatch.idProduto, produit.description");
            scriptSQL.append(" FROM itemdispatch");
            scriptSQL.append(" LEFT JOIN dispatch ON itemdispatch.idDispatch = dispatch.code");
            scriptSQL.append(" LEFT JOIN shop as shopExpediteur ON dispatch.idShop = shopExpediteur.code");
            scriptSQL.append(" LEFT JOIN shop as shopDestinataire ON itemdispatch.idShop = shopDestinataire.code");
            scriptSQL.append(" LEFT JOIN produit ON itemdispatch.idProduit = produit.code");
            scriptSQL.append(" WHERE dispatch.code=? AND produit.code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeDispatch);
            prs.setInt(2, codeProduit);
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    Dispatch dispatch = new Dispatch(res.getInt(2));
                    dispatch.setDateHeure(res.getTimestamp(3));
                    dispatch.setValide(res.getInt(4) == 1);

                    Shop shopExpediteur = new Shop(res.getInt(5));
                    shopExpediteur.setNom(res.getString(6));
                    dispatch.setShop(shopExpediteur);

                    Produit produit = new Produit(res.getInt(9));
                    produit.setDescription(res.getString(10));

                    ItemDispatch itemDispatch = new ItemDispatch(dispatch, produit);
                    itemDispatch.setQuantiteProduit(res.getBigDecimal(1));
                    itemDispatch.setQuantiteProduit(res.getBigDecimal(3));

                    Shop shopDestinataire = new Shop(res.getInt(7));
                    shopDestinataire.setNom(res.getString(8));
                    itemDispatch.setShop(shopDestinataire);

                    prs.close();
                    res.close();
                    conexao.close();

                    return itemDispatch;
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return null;
    }

}
