package com.example.oopphase2;

import com.example.oopphase2.Phase1.Category;
import com.example.oopphase2.Phase1.Event;
import com.example.oopphase2.Phase1.Organizer;
import com.example.oopphase2.Phase1.Room;
import com.example.oopphase2.Phase1.*;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EventFormController {

    @FXML
    private TextField titleField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField dateField;
    @FXML
    private TextField timeField;
    @FXML
    private TextField priceField;
    @FXML
    private ComboBox<Category> categoryComboBox;
    @FXML
    private ComboBox<Room> roomComboBox;


    private Organizer organizer;
    private Event eventToEdit;

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }

    public void setEventToEdit(Event event) {
        this.eventToEdit = event;
        if (event != null) {
            titleField.setText(event.getTitle());
            descriptionField.setText(event.getDescription()); // Assuming description is stored
            dateField.setText(event.getDate());
            timeField.setText(event.getTime());
            priceField.setText(String.valueOf(event.getTicketPrice()));
            categoryComboBox.setValue(event.getCategory());
            roomComboBox.setValue(event.getRoom());
        }
    }

    @FXML
    public void initialize() {
        // Populate the ComboBoxes with available categories and rooms
        categoryComboBox.getItems().addAll(Database.categories);
        roomComboBox.getItems().addAll(Database.rooms);
    }

    @FXML
    private void saveEvent() {
        if (organizer == null) {
            return;
        }

        String title = titleField.getText();
        String description = descriptionField.getText();
        String date = dateField.getText();
        String time = timeField.getText();
        double price;

        try {
            price = Double.parseDouble(priceField.getText());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ticket price. Please enter a numeric value.");
            return;
        }

        Category selectedCategory = categoryComboBox.getValue();
        Room selectedRoom = roomComboBox.getValue();

        if (selectedCategory == null || selectedRoom == null) {
            System.out.println("Please select both a category and a room.");
            return;
        }
        if (eventToEdit == null) {
            // Create a new event
            new Event(Database.events.size() + 1, title, description, date, time, price, selectedRoom, organizer, selectedCategory);
        } else {
            // Edit the existing event
            eventToEdit.setTitle(title, organizer);
            eventToEdit.setDescription(description, organizer);
            eventToEdit.setDate(date, organizer);
            eventToEdit.setTime(time, organizer);
            eventToEdit.setTicketPrice(price, organizer);
            eventToEdit.setRoom(selectedRoom, organizer);
            eventToEdit.setCategory(selectedCategory, organizer);
        }

        // Close the form
        Stage stage = (Stage) titleField.getScene().getWindow();
        stage.close();
    }
}