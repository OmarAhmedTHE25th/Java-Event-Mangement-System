package com.example.oopphase2.Phase1;

import java.util.ArrayList;

public class Organizer extends User {
    private final ArrayList<Event> organizedEvents = new ArrayList<>();

    public Organizer() {
    }

    public Organizer(String username, String password, String dateOfBirth) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty.");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty.");
        }
        if (dateOfBirth == null || dateOfBirth.trim().isEmpty()) {
            throw new IllegalArgumentException("Date of birth cannot be empty.");
        }

        this.Username = username;
        this.Password = password;
        this.dateOfBirth = dateOfBirth;
        Database.organizers.add(this);
    }

    public void addEvent(Event event) {
        if (event == null) {
            System.out.println("Cannot add null event.");
            return;
        }
        organizedEvents.add(event);
    }

    public ArrayList<Event> getOrganizedEvents() {
        return organizedEvents;
    }

    public void showMyEvents() {
        System.out.println("--- Events organized by: " + this.Username + " ---");
        for (Event e : organizedEvents) {
            System.out.println(e);
        }
    }

    public void updateEventDetails(int eventID, String newTitle, String newDescription, String newTime, String newDate) {
        for (Event e : organizedEvents) {
            if (e.getEventID() == eventID) {
                e.setTitle(newTitle, this);
                e.setDescription(newDescription, this);
                e.setTime(newTime, this);
                e.setDate(newDate, this);
                System.out.println("Event updated successfully.");
                return;
            }
        }
        System.out.println("Event not found.");
    }

    public void deleteEvent(int eventID) {
        for (Event e : organizedEvents) {
            if (e.getEventID() == eventID) {
                organizedEvents.remove(e);
                Database.events.remove(e);
                System.out.println("Event deleted successfully.");
                return;
            }
        }
        System.out.println("Event not found.");
    }


    @Override
    public String toString() {
        return "Organizer: " + Username + ", DOB: " + dateOfBirth;
    }
}