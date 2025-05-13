package com.example.oopphase2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class StartScreenController {

    @FXML
    public void LoginScreen() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Organiser-login.fxml"));
        Scene scene = new Scene(loader.load());

        Stage stage = new Stage();
        scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        stage.getIcons().add(new Image(getClass().getResource("Logo.png").toExternalForm()));
        stage.setTitle("Organizer Login");
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    public void Return() throws IOException {
        Phase2App Return = new Phase2App();
        Stage stage = new Stage();
        Return.start(stage);
    }


    public void Screen() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("newOrgAccount.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        scene.getStylesheets().add(getClass().getResource("sign up.css").toExternalForm());
        stage.getIcons().add(new Image(getClass().getResource("Logo.png").toExternalForm()));
        stage.setTitle("Organizer SignUP");
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }
}
