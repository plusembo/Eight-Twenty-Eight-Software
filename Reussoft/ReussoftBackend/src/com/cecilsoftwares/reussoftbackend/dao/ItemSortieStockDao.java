package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.Client;
import com.cecilsoftwares.reussoftmiddleend.model.ItemSortieStock;
import com.cecilsoftwares.reussoftmiddleend.model.PrixVenteProduit;
import com.cecilsoftwares.reussoftmiddleend.model.SortieStock;
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
public class ItemSortieStockDao {

    private StringBuilder scriptSQL;
    private static ItemSortieStockDao uniqueInstance;

    public ItemSortieStockDao() {
    }

    public static synchronized ItemSortieStockDao getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ItemSortieStockDao();
        }
        return uniqueInstance;
    }

    public List<ItemSortieStock> listerTousLesItemsSortieStock() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<ItemSortieStock> listeItemsSortieStock;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            listeItemsSortieStock = new ArrayList();

            scriptSQL = new StringBuilder("SELECT itemsortiestock.quantiteProduit,");
            scriptSQL.append(" itemsortiestock.idSortieStock, sortiestock.dateHeure,");
            scriptSQL.append(" sortiestock.idShop, shop.nom,");
            scriptSQL.append(" sortiestock.idClient, client.nom, client.entreprise, client.telephone,");
            scriptSQL.append(" itemsortiestock.idProduto, produit.description,");
            scriptSQL.append(" itemsortiestock.idPrixVenteProduit, prixventeproduit.valeurUSD, prixventeproduit.valeurFC, prixventeproduit.dateHeure");
            scriptSQL.append(" FROM itemsortiestock");
            scriptSQL.append(" LEFT JOIN sortiestock ON itemsortiestock.idEntreestock = entreestock.code");
            scriptSQL.append(" LEFT JOIN shop ON sortiestock.idShop = shop.code");
            scriptSQL.append(" LEFT JOIN client ON sortiestock.idClient = client.code");
            scriptSQL.append(" LEFT JOIN produit ON itemsortiestock.idProduit = produit.code");
            scriptSQL.append(" LEFT JOIN prixventeproduit ON itemsortiestock.idPrixVenteProduit = prixventeproduit.code");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    SortieStock sortieStock = new SortieStock(res.getInt(2));
                    sortieStock.setDateHeure(res.getTimestamp(3));

                    Shop shop = new Shop(res.getInt(4));
                    shop.setNom(res.getString(5));
                    sortieStock.setShop(shop);

                    Client client = new Client(res.getInt(6));
                    client.setNom(res.getString(7));
                    client.setEntreprise(res.getString(8));
                    client.setTelephone(res.getString(9));
                    sortieStock.setClient(client);

                    Produit produit = new Produit(res.getInt(10));
                    produit.setDescription(res.getString(11));

                    ItemSortieStock itemSortieStock = new ItemSortieStock(sortieStock, produit);
                    itemSortieStock.setQuantiteProduit(res.getBigDecimal(1));

                    PrixVenteProduit prixVenteProduit = new PrixVenteProduit(res.getInt(12));
                    prixVenteProduit.setValeurUSD(res.getBigDecimal(13));
                    prixVenteProduit.setValeurFC(res.getBigDecimal(14));
                    prixVenteProduit.setDateHeure(res.getTimestamp(15));
                    itemSortieStock.setPrixVenteProduit(prixVenteProduit);

                    listeItemsSortieStock.add(itemSortieStock);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeItemsSortieStock;
    }

    public List<ItemSortieStock> listerItemsSortieStockParCodeSortieStock(int codeSortieStock) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<ItemSortieStock> listeItemsSortieStock;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            listeItemsSortieStock = new ArrayList();

            scriptSQL = new StringBuilder("SELECT itemsortiestock.quantiteProduit,");
            scriptSQL.append(" itemsortiestock.idSortieStock, sortiestock.dateHeure,");
            scriptSQL.append(" sortiestock.idShop, shop.nom,");
            scriptSQL.append(" sortiestock.idClient, client.nom, client.entreprise, client.telephone,");
            scriptSQL.append(" itemsortiestock.idProduto, produit.description,");
            scriptSQL.append(" itemsortiestock.idPrixVenteProduit, prixventeproduit.valeurUSD, prixventeproduit.valeurFC, prixventeproduit.dateHeure");
            scriptSQL.append(" FROM itemsortiestock");
            scriptSQL.append(" LEFT JOIN sortiestock ON itemsortiestock.idEntreestock = entreestock.code");
            scriptSQL.append(" LEFT JOIN shop ON sortiestock.idShop = shop.code");
            scriptSQL.append(" LEFT JOIN client ON sortiestock.idClient = client.code");
            scriptSQL.append(" LEFT JOIN produit ON itemsortiestock.idProduit = produit.code");
            scriptSQL.append(" LEFT JOIN prixventeproduit ON itemsortiestock.idPrixVenteProduit = prixventeproduit.code");
            scriptSQL.append(" WHERE sortiestock.code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeSortieStock);
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    SortieStock sortieStock = new SortieStock(res.getInt(2));
                    sortieStock.setDateHeure(res.getTimestamp(3));

                    Shop shop = new Shop(res.getInt(4));
                    shop.setNom(res.getString(5));
                    sortieStock.setShop(shop);

                    Client client = new Client(res.getInt(6));
                    client.setNom(res.getString(7));
                    client.setEntreprise(res.getString(8));
                    client.setTelephone(res.getString(9));
                    sortieStock.setClient(client);

                    Produit produit = new Produit(res.getInt(10));
                    produit.setDescription(res.getString(11));

                    ItemSortieStock itemSortieStock = new ItemSortieStock(sortieStock, produit);
                    itemSortieStock.setQuantiteProduit(res.getBigDecimal(1));

                    PrixVenteProduit prixVenteProduit = new PrixVenteProduit(res.getInt(12));
                    prixVenteProduit.setValeurUSD(res.getBigDecimal(13));
                    prixVenteProduit.setValeurFC(res.getBigDecimal(14));
                    prixVenteProduit.setDateHeure(res.getTimestamp(15));
                    itemSortieStock.setPrixVenteProduit(prixVenteProduit);

                    listeItemsSortieStock.add(itemSortieStock);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeItemsSortieStock;
    }

    public List<ItemSortieStock> listerItemsSortieStockParCodeProduit(int codeProduit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<ItemSortieStock> listeItemsSortieStock;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            listeItemsSortieStock = new ArrayList();

            scriptSQL = new StringBuilder("SELECT itemsortiestock.quantiteProduit,");
            scriptSQL.append(" itemsortiestock.idSortieStock, sortiestock.dateHeure,");
            scriptSQL.append(" sortiestock.idShop, shop.nom,");
            scriptSQL.append(" sortiestock.idClient, client.nom, client.entreprise, client.telephone,");
            scriptSQL.append(" itemsortiestock.idProduto, produit.description,");
            scriptSQL.append(" itemsortiestock.idPrixVenteProduit, prixventeproduit.valeurUSD, prixventeproduit.valeurFC, prixventeproduit.dateHeure");
            scriptSQL.append(" FROM itemsortiestock");
            scriptSQL.append(" LEFT JOIN sortiestock ON itemsortiestock.idEntreestock = entreestock.code");
            scriptSQL.append(" LEFT JOIN shop ON sortiestock.idShop = shop.code");
            scriptSQL.append(" LEFT JOIN client ON sortiestock.idClient = client.code");
            scriptSQL.append(" LEFT JOIN produit ON itemsortiestock.idProduit = produit.code");
            scriptSQL.append(" LEFT JOIN prixventeproduit ON itemsortiestock.idPrixVenteProduit = prixventeproduit.code");
            scriptSQL.append(" WHERE produit.code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeProduit);
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    SortieStock sortieStock = new SortieStock(res.getInt(2));
                    sortieStock.setDateHeure(res.getTimestamp(3));

                    Shop shop = new Shop(res.getInt(4));
                    shop.setNom(res.getString(5));
                    sortieStock.setShop(shop);

                    Client client = new Client(res.getInt(6));
                    client.setNom(res.getString(7));
                    client.setEntreprise(res.getString(8));
                    client.setTelephone(res.getString(9));
                    sortieStock.setClient(client);

                    Produit produit = new Produit(res.getInt(10));
                    produit.setDescription(res.getString(11));

                    ItemSortieStock itemSortieStock = new ItemSortieStock(sortieStock, produit);
                    itemSortieStock.setQuantiteProduit(res.getBigDecimal(1));

                    PrixVenteProduit prixVenteProduit = new PrixVenteProduit(res.getInt(12));
                    prixVenteProduit.setValeurUSD(res.getBigDecimal(13));
                    prixVenteProduit.setValeurFC(res.getBigDecimal(14));
                    prixVenteProduit.setDateHeure(res.getTimestamp(15));
                    itemSortieStock.setPrixVenteProduit(prixVenteProduit);

                    listeItemsSortieStock.add(itemSortieStock);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeItemsSortieStock;
    }

    public ItemSortieStock selectionnerItemSortieStockParCodeSortieStockECodeProduit(int codeSortieStock, int codeProduit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("SELECT itemsortiestock.quantiteProduit,");
            scriptSQL.append(" itemsortiestock.idSortieStock, sortiestock.dateHeure,");
            scriptSQL.append(" sortiestock.idShop, shop.nom,");
            scriptSQL.append(" sortiestock.idClient, client.nom, client.entreprise, client.telephone,");
            scriptSQL.append(" itemsortiestock.idProduto, produit.description,");
            scriptSQL.append(" itemsortiestock.idPrixVenteProduit, prixventeproduit.valeurUSD, prixventeproduit.valeurFC, prixventeproduit.dateHeure");
            scriptSQL.append(" FROM itemsortiestock");
            scriptSQL.append(" LEFT JOIN sortiestock ON itemsortiestock.idEntreestock = entreestock.code");
            scriptSQL.append(" LEFT JOIN shop ON sortiestock.idShop = shop.code");
            scriptSQL.append(" LEFT JOIN client ON sortiestock.idClient = client.code");
            scriptSQL.append(" LEFT JOIN produit ON itemsortiestock.idProduit = produit.code");
            scriptSQL.append(" LEFT JOIN prixventeproduit ON itemsortiestock.idPrixVenteProduit = prixventeproduit.code");
            scriptSQL.append(" WHERE sortiestock.code=? AND produit.code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeSortieStock);
            prs.setInt(2, codeProduit);
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    SortieStock sortieStock = new SortieStock(res.getInt(2));
                    sortieStock.setDateHeure(res.getTimestamp(3));

                    Shop shop = new Shop(res.getInt(4));
                    shop.setNom(res.getString(5));
                    sortieStock.setShop(shop);

                    Client client = new Client(res.getInt(6));
                    client.setNom(res.getString(7));
                    client.setEntreprise(res.getString(8));
                    client.setTelephone(res.getString(9));
                    sortieStock.setClient(client);

                    Produit produit = new Produit(res.getInt(10));
                    produit.setDescription(res.getString(11));

                    ItemSortieStock itemSortieStock = new ItemSortieStock(sortieStock, produit);
                    itemSortieStock.setQuantiteProduit(res.getBigDecimal(1));

                    PrixVenteProduit prixVenteProduit = new PrixVenteProduit(res.getInt(12));
                    prixVenteProduit.setValeurUSD(res.getBigDecimal(13));
                    prixVenteProduit.setValeurFC(res.getBigDecimal(14));
                    prixVenteProduit.setDateHeure(res.getTimestamp(15));
                    itemSortieStock.setPrixVenteProduit(prixVenteProduit);

                    prs.close();
                    res.close();
                    conexao.close();

                    return itemSortieStock;
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return null;
    }

}
