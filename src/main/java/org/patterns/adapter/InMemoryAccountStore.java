package org.patterns.adapter;

import java.util.HashMap;

public class InMemoryAccountStore implements AccountStore{
    private HashMap<Integer,Account> accountList;

    public InMemoryAccountStore() {
        Account account1 = new Account("Thiwanka",1234,23400);
        Account account2 = new Account("Pamudu",4321,2400);
        accountList = new HashMap<>();
        accountList.put(account1.getAccNumber(),account1);
        accountList.put(account2.getAccNumber(),account2);

    }



    @Override
    public Account getAccount(int accNumber) {
        return accountList.get(accNumber);
    }
}
