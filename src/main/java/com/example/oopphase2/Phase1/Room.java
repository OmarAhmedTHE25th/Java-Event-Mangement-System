package com.example.oopphase2.Phase1;

public class Room {
    String roomID;
    String roomName;
    int capacity;
    String availableHours;

    Room() {
    }

    public Room(String roomID, String roomName, int capacity, String availableHours) {
        try {

            if (roomID == null || roomID.trim().isEmpty() || !roomID.matches("R\\d{3}")) {
                throw new IllegalArgumentException(" Room ID must be in format 'R###' (e.g. R001).\n");
            }

            if (roomName == null || roomName.trim().isEmpty() || roomName.length() < 3) {
                throw new IllegalArgumentException(" Room name must be at least 3 characters.\n");
            }

            if (capacity <= 0 || capacity > 1000) {
                throw new IllegalArgumentException(" Capacity must be between 1 and 1000.\n");
            }

            if (availableHours == null || availableHours.trim().isEmpty() || !availableHours.matches("[A-Za-z]+\\s+\\d{1,2}-\\d{1,2}")) {
                throw new IllegalArgumentException(" Available hours must follow format 'Day start-end' (e.g. Monday 10-12).\n");
            }

            this.roomID = roomID;
            this.roomName = roomName;
            this.capacity = capacity;
            this.availableHours = availableHours;
            System.out.println(" Room created successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(" Unexpected error: " + e.getMessage());
        }


        this.roomID = roomID;
        this.roomName = roomName;
        this.capacity = capacity;
        this.availableHours = availableHours;


        Database.rooms.add(this);
    }


    @Override
    public String toString() {
        return "Room → ID: " + roomID +
                ", Name: " + roomName +
                ", Capacity: " + capacity +
                ", Available Hours: " + availableHours;
    }
}