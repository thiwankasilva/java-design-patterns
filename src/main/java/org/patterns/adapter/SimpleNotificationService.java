package org.patterns.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SimpleNotificationService implements NotificationService {

    private final NotificationPreferenceStore notificationPreferenceStore;

    public SimpleNotificationService(NotificationPreferenceStore notificationPreferenceStore) {
        this.notificationPreferenceStore = notificationPreferenceStore;
    }

    @Override
    public void notify(String notificationType, Map<String, Object> context) {
        System.out.println("NotificationType : " + notificationType + " " + context);
        int accNumber = (int) context.get("AccountNumber");
        int amount = (int) context.get("amount");
        NotificationPreference notificationPreference = notificationPreferenceStore.get(accNumber);
        String message = "";
        if (notificationType.equals("withdraw")) {
            message = amount + " is successfully withdrawed from account " + accNumber;
        } else if (notificationType.equals("deposit")) {
            message = amount + " is successfully deposited to account " + accNumber;
        }

        notifyByChannelAndOperations(message, notificationPreference, notificationType);
    }

    private void notifyByChannelAndOperations(String message, NotificationPreference notificationPreference,
                                              String notificationType) {
       boolean isOn = false;
        Map<String,HashMap<String,Boolean>> notificationsSwitches = notificationPreference.getNotificationsSwitches();
        Set<String> keysToCheck = Set.of("all", "sms","email");
        Map<String, ?> switches = notificationsSwitches.get(notificationType);

        if(!notificationsSwitches.isEmpty() && notificationsSwitches.containsKey(notificationType))
        {
            isOn = switches != null && keysToCheck.stream().anyMatch(switches::containsKey);
        }
        if(isOn)
        {
            if(notificationsSwitches.get(notificationType).get("all"))
            {
                notifyViaEmail(notificationPreference.getEmail(),message);
                notifyViaSms(notificationPreference.getPhoneNumber(),message);
            } else if (notificationsSwitches.get(notificationType).get("sms")) {
                notifyViaSms(notificationPreference.getPhoneNumber(), message);
            }
            else
            {
                notifyViaEmail(notificationPreference.getEmail(), message);
            }
        }

    }

    private void notifyViaSms(String phoneNumber, String message) {
        if(!phoneNumber.isEmpty())
        {
            System.out.println("Sending sms : "+phoneNumber+" "+message);
        }
        else {
            System.out.println("Phone number not provided");
        }

    }

    private void notifyViaEmail(String email, String message) {
        if(!email.isEmpty())
        {
            System.out.println("Sending email : "+email+" "+message);
        }
        else {
            System.out.println("Email not provided");
        }

    }


    @Override
    public void updatePhone(int accNumber, String phoneNumber) {
        NotificationPreference notificationPreference = notificationPreferenceStore.get(accNumber);
        notificationPreference.setPhoneNumber(phoneNumber);
        notificationPreferenceStore.save(notificationPreference);
    }

    @Override
    public void updateEmail(int accNumber, String email) {
        NotificationPreference notificationPreference = notificationPreferenceStore.get(accNumber);
        notificationPreference.setEmail(email);
        notificationPreferenceStore.save(notificationPreference);
    }

    @Override
    public void updatePreference(int accNumber, String notificationType, String channel, boolean isOn) {

        NotificationPreference notificationPreference = notificationPreferenceStore.get(accNumber);
        if (!notificationPreference.getNotificationsSwitches().containsKey(notificationType)) {

            HashMap<String, Boolean> channelPref = new HashMap<>();
            channelPref.put(channel, isOn);
            if(notificationType.equals("all"))
            {
                notificationPreference.getNotificationsSwitches().put("withdraw", channelPref);
                notificationPreference.getNotificationsSwitches().put("deposit", channelPref);
            }else{
                notificationPreference.getNotificationsSwitches().put(notificationType, channelPref);
            }

        } else if (notificationPreference.getNotificationsSwitches().containsKey(notificationType)) {
            HashMap<String, Boolean> channelPref = notificationPreference.getNotificationsSwitches().get(notificationType);
            channelPref.put(channel, isOn);
        }

    }
}
