package org.patterns.adapter;

public class Account {
    private String name;
    private int accNumber;

    private int currentAmount;

    public Account(String name, int accNumber, int currentAmount) {
        this.name = name;
        this.accNumber = accNumber;
        this.currentAmount = currentAmount;
    }

    public void withdraw(int amount)
    {
        currentAmount = currentAmount - amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccNumber() {
        return accNumber;
    }


    public int getCurrentAmount() {
        return currentAmount;
    }

    public void deposit(int amount) {
        this.currentAmount = currentAmount + amount;
    }
}
