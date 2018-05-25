package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.AppConfigKS;
import com.cecilsoftwares.reussoftbackend.dao.ConnectionFactory;
import com.cecilsoftwares.reussoftmiddleend.model.AppConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class MainService {

    private static MainService uniqueInstance;

    public MainService() {
    }

    public static synchronized MainService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new MainService();
        }
        return uniqueInstance;
    }

    public void initialiserBaseDonnees() {
        try {
            Gson gson = new GsonBuilder().create();
            BufferedReader br;
            br = new BufferedReader(new FileReader("mason.ini"));

            AppConfig appConfig = gson.fromJson(br, AppConfig.class);
            AppConfigKS.getInstance().setAppConfig(appConfig);

        } catch (FileNotFoundException fnfe) {
            Logger.getLogger(MainService.class.getName()).log(Level.SEVERE, null, fnfe);
            JOptionPane.showMessageDialog(null, fnfe);
        }

        boolean connecte = false;
        do {
            try {
                System.out.println(ConnectionFactory.getInstance().habiliterConnection());
                connecte = true;
                System.out.println("SUCESS");

            } catch (ClassNotFoundException | SQLException ex) {
                System.out.println("Não conectou: " + ex);
            }
        } while (!connecte);
    }

    public String getMacAddress() throws UnknownHostException, SocketException {
        InetAddress address = InetAddress.getLocalHost();
        NetworkInterface ni = NetworkInterface.getByInetAddress(address);
        byte[] mac = ni.getHardwareAddress();

        String macAdress = "";
        for (int i = 0; i < mac.length; i++) {
            macAdress += mac[i];
        }
        return macAdress;
    }

}