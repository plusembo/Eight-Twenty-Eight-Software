package com.cecilsystems.mistersoftfrontend.controller;

import com.cecilsystems.mistersoftfrontend.MainApp;
import com.cecilsystems.mistersoftbackend.service.VersaoService;
import com.cecilsystems.mistersoftfrontend.enumerable.PathEnum;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author Plamedi L. Lusembo
 */
public class SplashLayoutController implements Initializable {

    private Stage splashStage;
    @FXML
    private Label lblVersao;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            lblVersao.setText(VersaoService.getInstance().selecionaUltimaVersao().toString());
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SplashLayoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
        longStart();
    }

    private void longStart() {
        Service<String> service = new Service() {
            @Override
            protected Task<String> createTask() {
                return new Task<String>() {
                    @Override
                    protected String call() throws Exception {
                        Thread.sleep(2750);
                        return null;
                    }
                };
            }
        };
        service.start();
        service.setOnSucceeded((WorkerStateEvent event) -> {
            try {
                Stage loginStage = new Stage();
                splashStage = (Stage) lblVersao.getScene().getWindow();
                AnchorPane loginAnchorPane = FXMLLoader.load(MainApp.class
                        .getResource(PathEnum.VIEW_PATH + "Login.fxml"));
                Scene scene = new Scene(loginAnchorPane);
                loginStage.getIcons().add(new Image(PathEnum.IMAGES_PATH + "mistersoftlogo.png"));
                loginStage.setResizable(false);
                loginStage.setMaximized(false);
                loginStage.setTitle("Login");
                loginStage.setScene(scene);
                loginStage.show();
                splashStage.close();
            } catch (IOException ex) {
                Logger.getLogger(SplashLayoutController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
