package com.example.oopphase2;


import com.example.oopphase2.Phase1.Event;
import com.example.oopphase2.Phase1.Organizer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class OrganizerViewController {

    @FXML
    private TextArea outputArea;

    private Organizer currentOrganizer;

    public void setOrganizer(Organizer organizer) {
        this.currentOrganizer = organizer;
    }

    public void Return() throws IOException {
        StartScreenController o1 = new StartScreenController();
        o1.LoginScreen();
    }

    @FXML
    private void showMyEvents() {
        if (currentOrganizer == null) {
            outputArea.setText("No organizer logged in.");
            return;
        }
        StringBuilder events = new StringBuilder("--- My Events ---\n");
        currentOrganizer.getOrganizedEvents().forEach(event -> events.append(event).append("\n"));
        outputArea.setText(events.toString());
    }

    @FXML
    private TextField eventIdField;

    private void openEventForm(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("event-form.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(getClass().getResource("scratch.css").toExternalForm());
            stage.getIcons().add(new Image(getClass().getResource("Logo.png").toExternalForm()));
            EventFormController controller = loader.getController();
            controller.setOrganizer(currentOrganizer);
            controller.setEventToEdit(event);

            stage.setTitle(event == null ? "Create Event" : "Edit Event");
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int parseInt(String input) {
        if (input == null || input.isEmpty()) {
            outputArea.setText("Please enter an event ID.");
            return -1;
        }
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            outputArea.setText("Invalid input. Please enter a numeric value.");
            return -1;
        }
    }

    private Event getEventById(int eventId) {
        return currentOrganizer.getOrganizedEvents().stream()
                .filter(event -> event.getEventID() == eventId)
                .findFirst()
                .orElse(null);
    }

    @FXML
    private void createEvent() {
        openEventForm(null);
    }

    @FXML
    private void editEvent() {
        if (currentOrganizer == null || currentOrganizer.getOrganizedEvents().isEmpty()) {
            outputArea.setText("No events to edit.");
            return;
        }
        int eventId = parseInt(eventIdField.getText());
        Event eventToEdit = getEventById(eventId);

        if (eventToEdit == null) {
            outputArea.setText("Event with ID " + eventId + " not found.");
            return;
        }
        openEventForm(eventToEdit);
    }

    @FXML
    private void deleteEvent() {
        if (currentOrganizer == null) {
            outputArea.setText("No organizer logged in.");
            return;
        }

        int eventId = parseInt(eventIdField.getText());
        Event eventToDelete = getEventById(eventId);
        if (eventToDelete == null) {
            outputArea.setText("Event with ID " + eventId + " not found.");
        } else {
            currentOrganizer.deleteEvent(eventToDelete.getEventID());
            outputArea.setText("Event deleted successfully.");
        }
    }
}
