package com.gdb.domain;

import com.gdb.exceptions.*;

public class SavingsAccount extends Account {
    private double minBalance = 1000.0;
    private double interestRate = 4.0;

    public SavingsAccount(double balance) {
        super("SAVINGS", balance);
    }

    public SavingsAccount(double balance, double minBalance, double interestRate) {
        super("SAVINGS", balance);
        this.minBalance = minBalance;
        this.interestRate = interestRate;
    }

    @Override
    public void withdraw(double amt, int pin)
            throws InvalidAmountException,
                   InsufficientBalanceException,
                   MinimumBalanceViolationException,
                   InactiveAccountException,
                   InvalidPinException,
                   AccountException {
        if ((getBalance() - amt) < minBalance) {
            throw new MinimumBalanceViolationException(
                "Cannot withdraw: balance would drop below minimum balance of " + minBalance
            );
        }
        super.withdraw(amt, pin);
    }

    @Override
    public void withdraw(double amt) throws AccountException {
        if ((getBalance() - amt) < minBalance) {
            throw new MinimumBalanceViolationException(
                "Cannot withdraw: balance would drop below minimum balance of " + minBalance
            );
        }
        super.withdraw(amt);
    }

    public void applyInterest() {
        double interest = getBalance() * (interestRate / 100.0);
        try {
            deposit(interest);
        } catch (InvalidAmountException | InactiveAccountException e) {
            System.err.println(e.getMessage());
        }
    }

    public double getMinimumBalance() {
        return minBalance;
    }

    public void setMinimumBalance(double minBalance) {
        this.minBalance = minBalance;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}