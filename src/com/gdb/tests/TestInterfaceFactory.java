package com.gdb.tests;

import com.gdb.domain.*;

public class TestInterfaceFactory {
    public static void main(String[] args) {
        System.out.println("=== Activity 11: Interface & Factory Pattern Test ===");

        IAccount savings = AccountFactory.createAccount("SAVINGS", 101, "Rajesh Sharma", 30, 10000.0);
        System.out.println("Factory created: " + savings.getAccountType() + " account for " + savings.getName());

        IAccount current = AccountFactory.createAccount("CURRENT", 102, "Priya Patel", 28, 15000.0);
        System.out.println("Factory created: " + current.getAccountType() + " account for " + current.getName());

        IAccount fd = AccountFactory.createAccount("FIXED_DEPOSIT", 103, "Amit Kumar", 45, 50000.0);
        System.out.println("Factory created: " + fd.getAccountType() + " account for " + fd.getName());

        IAccount salary = AccountFactory.createAccount("SALARY", 104, "Sneha Verma", 26, 25000.0);
        System.out.println("Factory created: " + salary.getAccountType() + " account for " + salary.getName());

        System.out.println("All accounts successfully created through AccountFactory!");
    }
}
