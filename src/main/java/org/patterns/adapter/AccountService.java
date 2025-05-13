package org.patterns.adapter;

import java.util.HashMap;
import java.util.Map;

public class AccountService {

    AccountStore accountStore;
    private final NotificationService notificationService;

    public AccountService(AccountStore accountStore, NotificationService notificationService) {
        this.accountStore = accountStore;
        this.notificationService = notificationService;
    }

    public void withdraw(int accNumber, int amount)
    {
        Account account = accountStore.getAccount(accNumber);
        if(amount > account.getCurrentAmount())
        {
            throw new RuntimeException("Insufficient Balance");
        }
        account.withdraw(amount);
        Map<String,Object> context = new HashMap<>(){{put("AccountNumber",accNumber);put("Amount",amount);}};
        notificationService.notify("withdraw",context);

    }
    public void deposit(int accNumber, int amount)
    {

        Account account = accountStore.getAccount(accNumber);
        if(amount < 0)
        {
            throw new RuntimeException("Error Negative input value");
        }
        account.deposit(amount);
        Map<String,Object> context = new HashMap<>(){{put("AccountNumber",accNumber);put("Amount",amount);}};
        notificationService.notify("deposit",context);
    }
}
