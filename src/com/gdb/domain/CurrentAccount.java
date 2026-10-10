package com.gdb.domain;

import com.gdb.exceptions.*;

public class CurrentAccount extends Account {
    private double overdraftLimit = 25000.0;

    public CurrentAccount(double balance) {
        super("CURRENT", balance);
    }

    public CurrentAccount(double balance, double overdraftLimit) {
        super("CURRENT", balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amt, int pin)
            throws InvalidAmountException,
                   InsufficientBalanceException,
                   MinimumBalanceViolationException,
                   InactiveAccountException,
                   InvalidPinException,
                   AccountException {

        checkActive();

        if (this.pin == null || this.pin != pin) {
            throw new InvalidPinException("Incorrect PIN or PIN is not set.");
        }

        if (amt <= 0) {
            throw new InvalidAmountException("Withdraw amount must be greater than 0.");
        }

        if (amt > (this.balance + this.overdraftLimit)) {
            throw new InsufficientBalanceException("Requested amount exceeds balance and overdraft limit.");
        }

        this.balance -= amt;
    }

    @Override
    public void withdraw(double amt) throws AccountException {
        checkActive();

        if (amt <= 0) {
            throw new InvalidAmountException("Withdraw amount must be greater than 0.");
        }

        if (amt > (this.balance + this.overdraftLimit)) {
            throw new InsufficientBalanceException("Requested amount exceeds balance and overdraft limit.");
        }

        this.balance -= amt;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }
}