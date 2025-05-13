package com.example.oopphase2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class Phase2App extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Phase2App.class.getResource("main.fxml"));
        System.out.println("FXML: " + Phase2App.class.getResource("main.fxml"));
        System.out.println("CSS: " + getClass().getResource("Main.css"));
        System.out.println("Logo: " + getClass().getResource("Logo.png"));

    Scene scene = new Scene(fxmlLoader.load(), 320, 240, Color.CHOCOLATE);
    scene.getStylesheets().add(getClass().getResource("Main.css").toExternalForm());
    stage.setTitle("Phase2");
    stage.setScene(scene);
    stage.getIcons().add(new Image(getClass().getResource("Logo.png").toExternalForm()));
    stage.setFullScreen(true);

    stage.setFullScreenExitHint("Press ESC to exit fullscreen");
    stage.show();


    }

}
