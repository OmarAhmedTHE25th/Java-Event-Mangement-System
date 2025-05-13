package com.example.oopphase2;

import com.example.oopphase2.Phase1.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class OrganiserLogin {
    @FXML
    public AnchorPane Pane;
    @FXML
    public TextField userField;
    @FXML
    public PasswordField passField;
    @FXML
    public Button login;
    @FXML
    public TextField outputArea;

    public void Olog() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("organizer-signup.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(loader.load());


        scene.getStylesheets().add(getClass().getResource("Account.css").toExternalForm());
        stage.getIcons().add(new Image(getClass().getResource("Logo.png").toExternalForm()));
        stage.setTitle("Account Page");
        stage.setScene(scene);
        stage.setFullScreen(true);


        stage.show();
    }


    public void Alog() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("admin-signup.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(loader.load());


        scene.getStylesheets().add(getClass().getResource("Account.css").toExternalForm());
        stage.getIcons().add(new Image(getClass().getResource("Logo.png").toExternalForm()));
        stage.setTitle("Account Page");
        stage.setScene(scene);
        stage.setFullScreen(true);


        stage.show();
    }


    @FXML
    public void OrgLoginLogic() throws IOException {


        String username = userField.getText();
        String password = passField.getText();

        Organizer matchingOrganizer = Database.organizers.stream()
                .filter(org -> org.Username.equals(username) && org.Password.equals(password))
                .findFirst()
                .orElse(null);

        Stage stage = new Stage();
        if (matchingOrganizer != null) {
            outputArea.setText("Login Successful!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("organizer-view.fxml"));
            stage = new Stage();
            Scene scene2 = new Scene(loader.load());
            OrganizerViewController controller = loader.getController();
            scene2.getStylesheets().add(getClass().getResource("scratch.css").toExternalForm());
            stage.getIcons().add(new Image(getClass().getResource("Logo.png").toExternalForm()));
            controller.setOrganizer(matchingOrganizer);

            stage.setTitle("Organizer Panel");
            stage.setScene(scene2);
            stage.setFullScreen(true);
            stage.show();
            Stage currentStage = (Stage) login.getScene().getWindow();
            currentStage.close();

        } else {
            outputArea.setText("Invalid username or password.");

        }

    }

    public void OrgReturn() throws IOException {
        OrganiserLogin o1 = new OrganiserLogin();
        o1.Olog();
    }


    public void AdmReturn() throws IOException {
        OrganiserLogin a1 = new OrganiserLogin();
        a1.Alog();
    }


    public void ATTlog() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("attendee-signup.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(loader.load());


        scene.getStylesheets().add(getClass().getResource("Account.css").toExternalForm());
        stage.getIcons().add(new Image(getClass().getResource("Logo.png").toExternalForm()));
        stage.setTitle("Account Page");
        stage.setScene(scene);
        stage.setFullScreen(true);


        stage.show();
    }


    @FXML
    public AnchorPane Pane2;
    @FXML
    public TextField usernameArea;
    @FXML
    public PasswordField passwordArea;
    @FXML
    public Button loginAtt;

    public TextField outputMessage1;

    public void ATT_Login_Logic() {
        String username = usernameArea.getText();
        String password = passwordArea.getText();

        Attendee matchingAttendee = Database.attendees.stream()
                .filter(att -> att.Username.equals(username) && att.Password.equals(password))
                .findFirst()
                .orElse(null);

        Stage stage;
        if (matchingAttendee != null) {
            outputMessage1.setText("Login Successful!");
            try {


                FXMLLoader loader = new FXMLLoader(getClass().getResource("AttendeeView.fxml"));
                stage = new Stage();
                Parent root = loader.load();

                AttendeeController attendeeController = loader.getController();
                attendeeController.setAttendee(matchingAttendee);
                Scene scene2 = new Scene(root);
                scene2.getStylesheets().add(getClass().getResource("scratch.css").toExternalForm());
                stage.getIcons().add(new Image(getClass().getResource("Logo.png").toExternalForm()));


                stage.setTitle("Attendee Panel");
                stage.setScene(scene2);
                stage.setFullScreen(true);
                stage.show();
                Stage currentStage = (Stage) loginAtt.getScene().getWindow();
                currentStage.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            outputMessage1.setText("Invalid username or password.");

        }
    }

    public void AttReturn() throws IOException {
        OrganiserLogin att1 = new OrganiserLogin();
        att1.ATTlog();
    }


    @FXML
    public AnchorPane Pane1;
    @FXML
    public TextField usernameField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public Button loginA;

    public TextField outputMessage;


    public void AdminLog() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        Admin matchingAdmin = Database.admins.stream()
                .filter(adm -> adm.Username.equals(username) && adm.Password.equals(password))
                .findFirst()
                .orElse(null);

        Stage stage;
        if (matchingAdmin != null) {
            outputMessage.setText("Login Successful!");
            try {


                FXMLLoader loader = new FXMLLoader(getClass().getResource("adminGUI.fxml"));
                stage = new Stage();
                Parent root = loader.load();

                AdminFrontend adminController = loader.getController();
                adminController.setAdmin(matchingAdmin);
                Scene scene2 = new Scene(root);
                scene2.getStylesheets().add(getClass().getResource("scratch.css").toExternalForm());
                stage.getIcons().add(new Image(getClass().getResource("Logo.png").toExternalForm()));


                stage.setTitle("Admin Panel");
                stage.setScene(scene2);
                stage.setFullScreen(true);
                stage.show();
                Stage currentStage = (Stage) loginA.getScene().getWindow();
                currentStage.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Failed to load adminGUI.fxml. Check the file path and controller.");
            }
        } else {
            outputMessage.setText("Invalid username or password.");

        }
    }


}
