package com.example.oopphase2.Phase1;

public class Wallet {
    private double balance;

    public Wallet(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.println("Added " + amount + " to the wallet. New balance is : " + this.balance);
        } else {
            System.out.println("Cannot add negative or zero amount.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Ticket succesfully paid");
            return true;
        }

        System.out.println("insufficient balance for buying ticket");
        return false;
    }

    @Override
    public String toString() {
        return "Wallet Balance: $" + String.format("%.2f", balance);
    }

}

