package com.example.oopphase2.Phase1;

import java.util.ArrayList;

public class Event {
    private int eventID;
    private String title;
    private String description;
    private String date;
    private String time;
    private Organizer organizer;
    private Category category;
    private double ticketPrice;
    private Room room;
    private final ArrayList<Attendee> attendees = new ArrayList<>();

    public Event(int eventID, String title, String description, String date, String time,
                 double ticketPrice, Room room, Organizer organizer, Category category) {
        setEventID(eventID, organizer);
        setTitle(title, organizer);
        setDescription(description, organizer);
        setDate(date, organizer);
        setTime(time, organizer);
        setTicketPrice(ticketPrice, organizer);
        setRoom(room, organizer);
        setOrganizer(organizer, organizer);
        this.category = category;

        Database.events.add(this);
        organizer.addEvent(this);
    }


    public ArrayList<Attendee> getAttendees() {
        return attendees;
    }

    public void addAttendee(Attendee attendee) {
        if (!attendees.contains(attendee)) {
            attendees.add(attendee);
        }
    }

    public void setEventID(int eventID, User user) {
        if (!(user instanceof Organizer)) {
            System.out.println("Only organizers can set event ID.");
            return;
        }
        if (eventID <= 0) {
            System.out.println("Event ID must be positive.");
            return;
        }
        this.eventID = eventID;
    }


    public int getEventID() {
        return eventID;
    }

    public void setTitle(String title, User user) {
        if (!(user instanceof Organizer)) {
            System.out.println("Only organizers can set the event title.");
            return;
        }
        if (title == null || title.trim().isEmpty()) {
            System.out.println("Event title cannot be empty.");
            return;
        }
        if (title.length() < 3 || title.length() > 50) {
            System.out.println("Event title must be between 3 and 50 characters.");
            return;
        }
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description, User user) {
        if (!(user instanceof Organizer)) {
            System.out.println("Only organizers can set the event description.");
            return;
        }
        if (description == null || description.trim().isEmpty()) {
            System.out.println("Description cannot be empty.");
            return;
        }
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDate(String date, User user) {
        if (!(user instanceof Organizer)) {
            System.out.println("Only organizers can set the event date.");
            return;
        }
        if (date == null || !date.matches("\\d{2}-\\d{2}-\\d{4}")) {
            System.out.println("Date must be in the format DD-MM-YYYY.");
            return;
        }
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setTime(String time, User user) {
        if (!(user instanceof Organizer)) {
            System.out.println("Only organizers can set the event time.");
            return;
        }
        if (time == null || !time.matches("\\d{1,2}:[0-5]\\d")) {
            System.out.println("Time must be in the format HH:MM.");
            return;
        }
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTicketPrice(double ticketPrice, User user) {
        this.ticketPrice = ticketPrice;
        if (!(user instanceof Organizer)) {
            System.out.println("Only organizers can set the ticket price.");
            return;
        }
        if (this.ticketPrice <= 0) {
            System.out.println("Ticket price must be positive.");
            return;
        }
    }

    public double getTicketPrice() {
        return ticketPrice;
    }


    public void setRoom(Room room, User user) {
        if (!(user instanceof Organizer)) {
            System.out.println("Only organizers can set the room.");
            return;
        }
        if (room == null) {
            System.out.println("Room cannot be null.");
            return;
        }
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }

    public void setOrganizer(Organizer organizer, User user) {
        if (!(user instanceof Organizer)) {
            System.out.println("Only organizers can set the organizer.");
            return;
        }
        if (organizer == null) {
            System.out.println("Organizer cannot be null.");
            return;
        }
        this.organizer = organizer;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category, User user) {
        if (!(user instanceof Organizer)) {
            System.out.println("Only organizers can set the category.");
            return;
        }
        if (category == null) {
            System.out.println("Category cannot be null.");
            return;
        }
        this.category = category;
    }

    @Override
    public String toString() {
        return "Event ID: " + eventID + ", Title: " + title + ", Date: " + date +
                ", Time: " + time + ", Category: " + category + ", Organizer: " + organizer.Username +
                ", Attendees: " + attendees.size();
    }


}
