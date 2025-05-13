package org.patterns.adapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AdapterMain {
    public static void main(String[] args) throws IOException {


        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        AccountStore store = new InMemoryAccountStore();
        NotificationPreferenceStore notificationPreferenceStore = new InMemoryNotificationStore();
        NotificationService notificationService = new SimpleNotificationService(notificationPreferenceStore);
        AccountService accountService = new AccountService(store,notificationService);

        while (true)
        {
            System.out.println("Enter Command :");
            String cmd = in.readLine();
            //withdraw 12344 50000
            //deposit 14243 50000
            //exit - break
            //set phone accNumber phoneNumber
            //set email accNumber email
            //set notification accountNo withdraw sms on/off
            //set notification accountNo deposit email on/off
            //set notification accountNo all sms on/off
            //set notification accountNo all email on/off
            //set notification accountNo all all on/off

            if (cmd.equals("exit")) return;
            String[] input = cmd.split(" ");

            if (input[0].equals("set")) {
                String updateChoice = input[1];
                switch (updateChoice)
                {
                    case "phone":
                        notificationService.updatePhone(Integer.parseInt(input[2]),input[3]);
                        break;
                    case "email":
                        notificationService.updateEmail(Integer.parseInt(input[2]),input[3]);
                        break;
                    case "notification":
                        String notificationType = input[3];
                        String channel = input[4];
                        boolean isOn = Boolean.parseBoolean(input[5]);
                        notificationService.updatePreference(Integer.parseInt(input[2]),notificationType,channel, isOn);
                        break;
                }
            } else if(input[0].equals("withdraw"))
            {
                int accNumber = Integer.parseInt(input[1]);
                int amount = Integer.parseInt(input[2]);
                accountService.withdraw(accNumber,amount);
            }
            else if (input[0].equals("deposit"))
            {
                int accNumber = Integer.parseInt(input[1]);
                int amount = Integer.parseInt(input[2]);
                 accountService.deposit(accNumber,amount);
            }
            else
            {
                System.out.println("Wrong Input");
            }
        }

    }
}
