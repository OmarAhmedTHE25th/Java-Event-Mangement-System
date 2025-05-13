package com.example.oopphase2;
import com.example.oopphase2.*;
import com.example.oopphase2.Phase1.Database;
import com.example.oopphase2.Phase1.Organizer;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SignUPscreen {

    public TextField newUserField;
    public TextField newPassField;
    public TextField newDateField;
    public TextArea Message;

    @FXML
    public void SignUPlogic() throws IOException {
        String newUser = newUserField.getText();
        String newPass = newPassField.getText();
        String newDate = newDateField.getText();

        if (newUserField.toString().isEmpty() || newPassField.toString().isEmpty()) {
            Message.setText("Fields cannot be empty.");
            return;
        }
        boolean exists = Database.organizers.stream()
                .anyMatch(org -> org.Username.equals(newUser));
        if (exists) {
            Message.setText("Username already taken.");
            return;
        }

        Organizer organizer = new Organizer(newUser, newPass, newDate);
        Database.organizers.add(organizer);
        Message.setText("Organizer successfully Created");


    }

    public void Return() throws IOException {
        OrganiserLogin o1 = new OrganiserLogin();
        o1.Olog();

    }
}
