package com.example.oopphase2;

import com.example.oopphase2.Phase1.Attendee;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AttendeeController {

    @FXML
    private Label balanceLabel;
    @FXML
    private TextField depositField;
    @FXML
    private TextField withdrawField;
    @FXML
    private Button searchEventButton;

    private Attendee currentAttendee;

    public void setAttendee(Attendee attendee) {
        this.currentAttendee = attendee;
        System.out.println("Logged in as: " + attendee);
    }

    @FXML
    private void initialize() {
        updateBalanceDisplay(0);
        currentAttendee = new Attendee("temp", "temp", "temp");
    }

    public void openEventSearch() {
        eventsearch searchWindow = new eventsearch(currentAttendee, () -> updateBalanceDisplay(currentAttendee.wallet.getBalance()));
        searchWindow.start(new Stage());
    }

    public void Return() throws IOException {
        AttendeeScreenController attendeeScreenController = new AttendeeScreenController();
        attendeeScreenController.AttLogin_screen();
    }

    @FXML
    private void handleDeposit() {
        try {
            double amount = Double.parseDouble(depositField.getText());
            currentAttendee.wallet.deposit(amount);
            updateBalanceDisplay(currentAttendee.wallet.getBalance());
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Enter a valid deposit amount.");
        }
    }

    @FXML
    private void handleWithdraw() {
        try {
            double amount = Double.parseDouble(withdrawField.getText());
            if (currentAttendee.wallet.withdraw(amount)) {
                updateBalanceDisplay(currentAttendee.wallet.getBalance());
            } else {
                showAlert("Insufficient Funds", "Not enough balance.");
            }
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Enter a valid withdrawal amount.");
        }
    }

    void updateBalanceDisplay(double amount) {
        balanceLabel.setText(String.format("%.2f", amount));
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
