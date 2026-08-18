package com.example.proyectoalkewallet.model;

public final class Cuenta {

    private static double saldo = 124.57;

    private Cuenta() {
    }

    public static double obtenerSaldo() {
        return saldo;
    }

    public static boolean depositar(double monto) {
        if (monto <= 0) {
            return false;
        }

        saldo += monto;
        return true;
    }

    public static boolean retirar(double monto) {
        if (monto <= 0 || monto > saldo) {
            return false;
        }

        saldo -= monto;
        return true;
    }
}
