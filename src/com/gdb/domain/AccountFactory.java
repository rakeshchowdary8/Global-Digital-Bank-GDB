package com.gdb.domain;

public class AccountFactory {

    public static IAccount createAccount(String type, int accountNumber, String name, int age, double balance) {
        if (type == null) {
            throw new IllegalArgumentException("Account type cannot be null.");
        }

        switch (type.toUpperCase().trim()) {
            case "SAVINGS":
                return new SavingsAccount(accountNumber, name, age, balance);
            case "CURRENT":
                return new CurrentAccount(accountNumber, name, age, balance);
            case "FIXED_DEPOSIT":
            case "FD":
                return new FixedDepositAccount(accountNumber, name, age, balance);
            case "SALARY":
                return new SalaryAccount(accountNumber, name, age, balance);
            default:
                throw new IllegalArgumentException("Unknown account type: " + type);
        }
    }

    public static IAccount createAccount(String type, int accountNumber, String name, double balance) {
        return createAccount(type, accountNumber, name, 25, balance);
    }

    public static IAccount createAccount(String type, String name, double balance) {
        return createAccount(type, 1000 + (int)(Math.random() * 9000), name, 25, balance);
    }

    public static IAccount createAccount(String type, double balance) {
        if (type == null) {
            throw new IllegalArgumentException("Account type cannot be null.");
        }

        switch (type.toUpperCase().trim()) {
            case "SAVINGS":
                return new SavingsAccount(balance);
            case "CURRENT":
                return new CurrentAccount(balance);
            case "FIXED_DEPOSIT":
            case "FD":
                return new FixedDepositAccount(balance);
            case "SALARY":
                return new SalaryAccount(balance);
            default:
                throw new IllegalArgumentException("Unknown account type: " + type);
        }
    }
}
