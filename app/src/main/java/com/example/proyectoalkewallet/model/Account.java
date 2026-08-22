package com.example.proyectoalkewallet.model;

public final class Account {

    private static double balance = 124.57;

    private Account() {
    }

    public static double getBalance() {
        return balance;
    }

    public static boolean deposit(double amount) {
        if (amount <= 0) {
            return false;
        }

        balance += amount;
        return true;
    }

    public static boolean withdraw(double amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        }

        balance -= amount;
        return true;
    }
}
