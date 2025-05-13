package com.example.oopphase2.Phase1;

public class Category {

    private String categoryName;

    public Category(String categoryName) {
        if (categoryName == null || categoryName.trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty.");
        }
        this.categoryName = categoryName;
        Database.categories.add(this); // self-register
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName, User user) {

        if (!(user instanceof Admin)) {
            System.out.println("Only Admin can modify the category name.");
            return;
        }
        if (categoryName == null || categoryName.trim().isEmpty()) {
            System.out.println("Category name cannot be null or empty.");
            return;
        }
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Category: " + categoryName;
    }
}
