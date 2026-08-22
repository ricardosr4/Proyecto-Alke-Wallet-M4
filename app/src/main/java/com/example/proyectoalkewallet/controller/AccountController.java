package com.example.proyectoalkewallet.controller;

import com.example.proyectoalkewallet.model.Account;

public class AccountController {

    public double getBalance() {
        return Account.getBalance();
    }

    public boolean deposit(String amountText) {
        Double amount = parseAmount(amountText);
        return amount != null && Account.deposit(amount);
    }

    public boolean withdraw(String amountText) {
        Double amount = parseAmount(amountText);
        return amount != null && Account.withdraw(amount);
    }

    private Double parseAmount(String amountText) {
        if (amountText == null || amountText.trim().isEmpty()) {
            return null;
        }

        try {
            return Double.parseDouble(amountText.trim().replace(',', '.'));
        } catch (NumberFormatException exception) {
            return null;
        }
    }
}
