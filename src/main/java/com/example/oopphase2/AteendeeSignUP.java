package com.example.oopphase2;

import com.example.oopphase2.Phase1.Attendee;
import com.example.oopphase2.Phase1.Database;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AteendeeSignUP {
    public TextField newUserField;
    public TextField newPassField;
    public TextField newDateField;
    public TextArea Message;

    public void SignUPlogic() {
        String newUser = newUserField.getText();
        String newPass = newPassField.getText();
        String newDate = newDateField.getText();

        if (newUserField.toString().isEmpty() || newPassField.toString().isEmpty()) {
            Message.setText("Fields cannot be empty.");
            return;
        }
        boolean exists = Database.admins.stream()
                .anyMatch(org -> org.Username.equals(newUser));
        if (exists) {
            Message.setText("Username already taken.");
            return;
        }

        Attendee attendee = new Attendee(newUser, newPass, newDate);
        Database.attendees.add(attendee);
        Message.setText("Atendee successfully Created");


    }

    public void Return() throws IOException {
        OrganiserLogin a1 = new OrganiserLogin();
        a1.ATTlog();

    }
}
