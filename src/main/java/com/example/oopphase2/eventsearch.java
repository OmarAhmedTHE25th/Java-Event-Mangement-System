package com.example.oopphase2;

import com.example.oopphase2.Phase1.Attendee;
import com.example.oopphase2.Phase1.Database;
import com.example.oopphase2.Phase1.Event;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;

public class eventsearch {

    private List<Event> allEvents;
    private final ObservableList<Event> filteredEvents = FXCollections.observableArrayList();
    private final Attendee currentAttendee;
    private final Runnable onBalanceUpdate;

    // Constructor to accept the current attendee
    public eventsearch(Attendee attendee, Runnable onBalanceUpdate) {
        this.currentAttendee = attendee;
        this.onBalanceUpdate = onBalanceUpdate;
    }

    public void start(Stage newStage) {
        Stage primaryStage = new Stage();
        allEvents = Database.events;

        VBox root = new VBox(10);
        TextField searchField = new TextField();
        searchField.setPromptText("Search by name, category, or room");

        ListView<Event> eventListView = new ListView<>();
        eventListView.setItems(filteredEvents);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            String lowerCaseFilter = newValue.toLowerCase();
            filteredEvents.setAll(
                    allEvents.stream()
                            .filter(event ->
                                    event.getTitle().toLowerCase().contains(lowerCaseFilter) ||
                                            event.getCategory().toString().toLowerCase().contains(lowerCaseFilter) ||
                                            event.getRoom().toString().toLowerCase().contains(lowerCaseFilter))
                            .collect(Collectors.toList())
            );
        });

        eventListView.setOnMouseClicked(e -> {
            Event selectedEvent = eventListView.getSelectionModel().getSelectedItem();
            if (selectedEvent != null) {
                StringBuilder info = new StringBuilder();
                info.append("Category: ").append(selectedEvent.getCategory()).append("\n")
                        .append("Room: ").append(selectedEvent.getRoom()).append("\n")
                        .append("Date: ").append(selectedEvent.getDate()).append("\n")
                        .append("Description: ").append(selectedEvent.getDescription()).append("\n")
                        .append("Ticket Price: ").append(selectedEvent.getTicketPrice()).append("\n");

                boolean alreadyRegistered = selectedEvent.getAttendees().contains(currentAttendee);

                if (alreadyRegistered) {
                    info.append("\nYou are already registered for this event.");
                } else {
                    if (currentAttendee.wallet.withdraw(selectedEvent.getTicketPrice())) {
                        selectedEvent.addAttendee(currentAttendee);
                        info.append("\nYou have been successfully registered for this event.");
                        if (onBalanceUpdate != null) {
                            onBalanceUpdate.run(); // Trigger UI update
                        }
                    } else {
                        info.append("\nInsufficient balance to register for this event.");
                    }
                }

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Event Details");
                alert.setHeaderText(selectedEvent.getTitle());
                alert.setContentText(info.toString());
                alert.showAndWait();
            }
        });

        root.getChildren().addAll(searchField, eventListView);
        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Event Search");
        primaryStage.show();

        filteredEvents.setAll(allEvents);
        scene.getStylesheets().add(getClass().getResource("scratch.css").toExternalForm());
        primaryStage.getIcons().add(new Image(getClass().getResource("Logo.png").toExternalForm()));
    }
}
