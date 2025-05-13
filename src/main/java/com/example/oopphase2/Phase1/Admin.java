package com.example.oopphase2.Phase1;

import java.util.ArrayList;

public class Admin extends User {

    Admin() {
    }

    public Admin(String Username, String Password, String dateOFBirth) {
        this.Username = Username;
        this.Password = Password;
        this.dateOfBirth = dateOFBirth;
        Database.admins.add(this);
    }

    private final ArrayList<Category> organizedCategs = new ArrayList<>();

    public ArrayList<Category> getOrganizedCategs() {
        return Database.categories;
    }

    @Override
    public String toString() {
        return "Admin → Username: " + Username +
                ", DOB: " + dateOfBirth;
    }

    public void createCategory(String name) {
        Category newCategory = new Category(name);
        System.out.println("Category created: " + name);
    }

    public void showCategories() {
        System.out.println("All categories:");
        for (Category c : Database.categories) {
            System.out.println("- " + c.getCategoryName());
        }
    }

    public void updateCategory(int index, String newName) {
        if (index < 0 || index >= Database.categories.size()) {
            System.out.println("Invalid category index.");
            return;
        }
        Database.categories.get(index).setCategoryName(newName, this);
        System.out.println("Category updated.");
    }

    public void deleteCategory(int index) {
        if (index < 0 || index >= Database.categories.size()) {
            System.out.println("Invalid category index.");
            return;
        }
        Database.categories.remove(index);
        System.out.println("Category deleted.");
    }

    public String showAll() {
        StringBuilder sb = new StringBuilder();

        // Attendees
        sb.append("---------------------- Attendees Information --------------------------\n");
        if (Database.attendees.isEmpty()) {
            sb.append("No attendees found.\n");
        } else {
            for (int i = 0; i < Database.attendees.size(); i++) {
                sb.append(i + 1).append(". ").append(Database.attendees.get(i)).append("\n");
            }
        }
        sb.append("\n");

        // Events
        sb.append("---------------------- Events Information --------------------------\n");
        if (Database.events.isEmpty()) {
            sb.append("No events found.\n");
        } else {
            for (int i = 0; i < Database.events.size(); i++) {
                sb.append(i + 1).append(". ").append(Database.events.get(i)).append("\n");
            }
        }
        sb.append("\n");

        // Rooms
        sb.append("---------------------- Rooms Information --------------------------\n");
        if (Database.rooms.isEmpty()) {
            sb.append("No rooms found.\n");
        } else {
            for (int i = 0; i < Database.rooms.size(); i++) {
                sb.append(i + 1).append(". ").append(Database.rooms.get(i)).append("\n");
            }
        }

        return sb.toString();
    }

}
