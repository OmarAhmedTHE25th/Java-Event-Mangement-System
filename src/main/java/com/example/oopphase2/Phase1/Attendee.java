package com.example.oopphase2.Phase1;

import java.util.Scanner;

public class Attendee extends User {
    public Wallet wallet;

    Attendee() {
        this.wallet = new Wallet(0);
    }

    public Attendee(String Username, String Password, String dateOfBirth) {
        this.Username = Username;
        this.Password = Password;
        this.dateOfBirth = dateOfBirth;
        this.wallet = new Wallet(0);

    }

    public void attendeeSelectEvent() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome " + this.Username + "! Here are the available events:");

        for (Event e : Database.events) {
            if (!e.getAttendees().contains(this)) {
                System.out.println("Event ID: " + e.getEventID() + " | " + e.getTitle() +
                        " | Price: $" + e.getTicketPrice());
            }
        }

        System.out.print("Enter the ID of the event you want to join: ");
        int selectedID = scanner.nextInt();

        for (Event e : Database.events) {

            if (e.getEventID() == selectedID) {
                if (e.getAttendees().contains(this)) {
                    System.out.println("You've already joined this event.");
                    return;
                }

                if (this.wallet.withdraw(e.getTicketPrice())) {
                    e.addAttendee(this);
                    System.out.println("You've successfully joined " + e.getTitle() + "!");
                } else {
                    System.out.println("Insufficient balance to join this event.");
                }
                return;
            }
        }


        System.out.println("No event found with that ID.");
    }

    @Override
    public String toString() {
        return "Attendee → Username: " + Username +
                ", DOB: " + dateOfBirth +
                ", " + wallet;
    }

    public void setUsername(String username) {
    }

    public void setPassword(String password) {

    }

    public void setDob(String dob) {
    }

    enum Gender {M, F}
}
