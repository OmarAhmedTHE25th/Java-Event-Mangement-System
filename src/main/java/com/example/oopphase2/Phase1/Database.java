package com.example.oopphase2.Phase1;

import java.util.ArrayList;


public class Database {
    public static ArrayList<Attendee> attendees = new ArrayList<>();
    public static ArrayList<Admin> admins = new ArrayList<>();
    public static ArrayList<Organizer> organizers = new ArrayList<>();
    public static ArrayList<Event> events = new ArrayList<>();
    public static ArrayList<Room> rooms = new ArrayList<>();
    public static ArrayList<Category> categories = new ArrayList<>();

    static {
        // Add sample Admins
        admins.add(new Admin("admin1", "password1", "01-01-1980"));
        admins.add(new Admin("admin2", "password2", "02-02-1985"));

        // Add sample Organizers
        Organizer organizer1 = new Organizer("organizer1", "password1", "03-03-1990");
        Organizer organizer2 = new Organizer("organizer2", "password2", "04-04-1992");
        organizers.add(organizer1);
        organizers.add(organizer2);

        // Add sample Attendees
        attendees.add(new Attendee("attendee1", "password1", "05-05-2000"));
        attendees.add(new Attendee("attendee2", "password2", "06-06-2001"));

        // Add sample Categories
        Category category1 = new Category("Music");
        Category category2 = new Category("Technology");

        // Add sample Rooms
        Room room1 = new Room("R001", "Main Hall", 500, "Monday 9-17");
        Room room2 = new Room("R002", "Conference Room", 100, "Tuesday 10-16");

        // Add sample Events
        Event event1 = new Event(1, "Concert", "Live music concert", "10-10-2023", "18:00", 50.0, room1, organizer1, category1);
        Event event2 = new Event(2, "Tech Talk", "Discussion on AI", "15-10-2023", "14:00", 20.0, room2, organizer2, category2);
    }
}