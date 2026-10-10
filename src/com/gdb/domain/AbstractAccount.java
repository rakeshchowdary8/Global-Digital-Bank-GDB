package com.gdb.domain;

import com.gdb.exceptions.*;

public abstract class AbstractAccount implements IAccount {
    protected static final int MIN_AGE = 18;
    protected static final int MIN_PIN = 1000;
    protected static final int MAX_PIN = 9999;

    protected int accountNumber;
    protected String name;
    protected int age;
    protected double balance;
    protected String accountType;
    protected String status;
    protected String pin;

    public AbstractAccount() {
        this.status = "Active";
        this.pin = "1234";
    }

    public AbstractAccount(String accountType, double balance) {
        this.accountType = accountType;
        this.balance = balance;
        this.status = "Active";
        this.pin = "1234";
    }

    public AbstractAccount(int accountNumber, String name, double balance, String accountType) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
        this.accountType = accountType;
        this.status = "Active";
        this.pin = "1234";
    }

    public AbstractAccount(int accountNumber, String name, int age, double balance, String accountType) throws IllegalArgumentException {
        if (age < MIN_AGE) {
            throw new IllegalArgumentException("Age must be 18 or older to open an account.");
        }
        this.accountNumber = accountNumber;
        this.name = name;
        this.age = age;
        this.balance = balance;
        this.accountType = accountType;
        this.status = "Active";
        this.pin = "1234";
    }

    // Concrete Template Method for Withdrawal
    @Override
    public void withdraw(double amount, String pin) throws AccountException {
        // Step 1: Validate PIN
        if (!validatePin(pin)) {
            throw new InvalidPinException("Incorrect PIN or PIN is not set.");
        }

        // Step 2: Validate account status
        checkActive();

        // Step 3: Validate amount
        if (amount <= 0) {
            throw new InvalidAmountException("Withdraw amount must be greater than 0.");
        }

        // Step 4: Execute subclass-specific debit logic
        processDebit(amount);
    }

    @Override
    public void withdraw(double amount, int pin) throws AccountException {
        withdraw(amount, String.valueOf(pin));
    }

    @Override
    public void withdraw(double amount) throws AccountException {
        withdraw(amount, this.pin != null ? this.pin : "1234");
    }

    // Primitive hook for Template Method Pattern
    protected abstract void processDebit(double amount) throws AccountException;

    // Shared concrete deposit method
    @Override
    public void deposit(double amount) throws InvalidAmountException, InactiveAccountException {
        checkActive();
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive: " + amount);
        }
        this.balance += amount;
    }

    // Shared concrete PIN validation
    public boolean validatePin(String pin) {
        return this.pin != null && this.pin.equals(pin);
    }

    public boolean validatePin(int pin) {
        return validatePin(String.valueOf(pin));
    }

    public boolean verifyPin(int pin) {
        return validatePin(pin);
    }

    public boolean verifyPin(String pin) {
        return validatePin(pin);
    }

    public boolean hasPin() {
        return this.pin != null;
    }

    public void setPin(int pin) throws IllegalArgumentException {
        if (pin < MIN_PIN || pin > MAX_PIN) {
            throw new IllegalArgumentException("PIN must be a 4-digit number (1000 to 9999).");
        }
        this.pin = String.valueOf(pin);
    }

    public void setPin(String pin) throws IllegalArgumentException {
        if (pin == null || pin.length() != 4 || !pin.matches("\\d{4}")) {
            throw new IllegalArgumentException("PIN must be a 4-digit number (1000 to 9999).");
        }
        this.pin = pin;
    }

    // Shared concrete changePin method
    public void changePin(String oldPin, String newPin) throws InvalidPinException, IllegalArgumentException {
        if (!validatePin(oldPin)) {
            throw new InvalidPinException("Incorrect old PIN.");
        }
        if (newPin == null || newPin.length() != 4 || !newPin.matches("\\d{4}")) {
            throw new IllegalArgumentException("New PIN must be a 4-digit number (1000 to 9999).");
        }
        this.pin = newPin;
    }

    public void changePin(int oldPin, int newPin) throws InvalidPinException, IllegalArgumentException {
        changePin(String.valueOf(oldPin), String.valueOf(newPin));
    }

    // Shared concrete displayAccountInfo method
    @Override
    public void displayAccountInfo() {
        System.out.println(String.format(
            "Account #%d | %s (%d yrs) | %s | Rs %.1f | %s | PIN: %s",
            accountNumber, (name != null ? name : "N/A"), age, accountType, balance, status, (pin != null ? "Yes" : "No")
        ));
    }

    public void checkActive() throws InactiveAccountException {
        if (!"Active".equalsIgnoreCase(this.status)) {
            throw new InactiveAccountException("Cannot perform operation on an inactive account.");
        }
    }

    public void closeAccount() throws IllegalStateException {
        if ("Inactive".equalsIgnoreCase(this.status)) {
            throw new IllegalStateException("Account is already closed.");
        }
        this.status = "Inactive";
    }

    public void reopenAccount() throws IllegalStateException {
        if ("Active".equalsIgnoreCase(this.status)) {
            throw new IllegalStateException("Account is already active.");
        }
        this.status = "Active";
    }

    // Getters and Setters
    @Override
    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getCustomerName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    @Override
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPin() {
        return pin;
    }
}
