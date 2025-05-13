package com.example.oopphase2;

import com.example.oopphase2.Phase1.Admin;
import com.example.oopphase2.Phase1.Category;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class AdminFrontend {
    @FXML
    public TextArea outputArea;
    private Admin currentAdmin;


    public void setAdmin(Admin admin) {
        this.currentAdmin = admin;
        outputArea.setText("Logged in as: " + admin.Username); // Optional confirmation
    }

    public void Return() throws IOException {
        AdminStartScreenController a1 = new AdminStartScreenController();
        a1.AdminLOG();
    }

    // Create a new category
    @FXML
    public void newCategory() {
        if (currentAdmin == null) {
            outputArea.setText("No admin logged in.");
            return;
        }

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Create Category");
        dialog.setHeaderText("Enter Category Name");
        Optional<String> result = dialog.showAndWait();

        result.ifPresent(name -> {
            currentAdmin.createCategory(name);
            outputArea.setText("Category created: " + name);
        });
    }

    // Show all categories
    @FXML
    public void showCategories() {
        if (currentAdmin == null) {
            outputArea.setText("No admin logged in.");
            return;
        }
        System.out.println("Categories in organizedCategs: " + currentAdmin.getOrganizedCategs());
        StringBuilder sb = new StringBuilder("--- My Categories ---\n");
        ArrayList<Category> categories = currentAdmin.getOrganizedCategs();

        if (categories.isEmpty()) {
            sb.append("No categories found.\n");
        } else {
            for (Category category : categories) {
                sb.append("- ").append(category.getCategoryName()).append("\n");
            }
        }

        outputArea.setText(sb.toString());
    }

    // Edit a category
    @FXML
    public void editCategories() {
        if (currentAdmin == null) {
            outputArea.setText("No admin logged in.");
            return;
        }

        TextInputDialog indexDialog = new TextInputDialog();
        indexDialog.setTitle("Edit Category");
        indexDialog.setHeaderText("Enter Category Index");
        Optional<String> indexResult = indexDialog.showAndWait();

        if (indexResult.isPresent()) {
            try {
                int index = Integer.parseInt(indexResult.get());
                TextInputDialog nameDialog = new TextInputDialog();
                nameDialog.setTitle("Edit Category");
                nameDialog.setHeaderText("Enter New Name");
                Optional<String> nameResult = nameDialog.showAndWait();

                nameResult.ifPresent(newName -> {
                    currentAdmin.updateCategory(index, newName);
                    outputArea.setText("Category updated to: " + newName);
                });
            } catch (NumberFormatException e) {
                outputArea.setText("Invalid index. Please enter a number.");
            }
        }
    }

    // Delete a category
    @FXML
    public void deleteCategory() {
        if (currentAdmin == null) {
            outputArea.setText("No admin logged in.");
            return;
        }

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Delete Category");
        dialog.setHeaderText("Enter Category Index");
        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            try {
                int index = Integer.parseInt(result.get());
                currentAdmin.deleteCategory(index);
                outputArea.setText("Category deleted.");
            } catch (NumberFormatException e) {
                outputArea.setText("Invalid index. Please enter a number.");
            }
        }
    }

    // Show all attendees, events, and rooms
    @FXML
    public void showAll() {
        if (currentAdmin == null) {
            outputArea.setText("No admin logged in.");
            return;
        }
        String allInfo = currentAdmin.showAll();
        outputArea.setText(allInfo);

    }
}