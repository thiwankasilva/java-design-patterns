package org.patterns.adapter;

import java.util.HashMap;
import java.util.Map;
public class SimpleNotificationService implements NotificationService {

    private final NotificationPreferenceStore notificationPreferenceStore;
    private final EmailSender emailSender;

    private final SMSSender smsSender;
    public SimpleNotificationService(NotificationPreferenceStore notificationPreferenceStore, EmailSender emailSender, SMSSender smsSender) {
        this.notificationPreferenceStore = notificationPreferenceStore;
        this.emailSender = emailSender;
        this.smsSender = smsSender;
    }

    @Override
    public void notify(String notificationType, Map<String, Object> context) {
        System.out.println("NotificationType : " + notificationType + " " + context);
        int accNumber = (int) context.get("AccountNumber");
        NotificationPreference notificationPreference = notificationPreferenceStore.get(accNumber);

        notifyByChannelAndOperations(context, notificationPreference, notificationType);
    }

    private void notifyByChannelAndOperations(Map<String, Object> context, NotificationPreference notificationPreference,
                                              String notificationType) {

        Map<String, HashMap<String, Boolean>> notificationsSwitches = notificationPreference.getNotificationsSwitches();

        if (notificationsSwitches == null || notificationsSwitches.isEmpty()) {
            System.out.println("Notification switches are empty or null.");
            return;
        }

        if (!notificationsSwitches.containsKey(notificationType)) {
            System.out.println("No switches configured for notification type: " + notificationType);
            return;
        }

        Map<String, Boolean> switches = notificationsSwitches.get(notificationType);
        if (switches == null) {
            System.out.println("Switches map for type '" + notificationType + "' is null.");
            return;
        }

        // Safely extract the values
        Boolean allSwitch = switches.getOrDefault("all", false);
        Boolean smsSwitch = switches.getOrDefault("sms", false);
        Boolean emailSwitch = switches.getOrDefault("email", false);

        boolean isOn = allSwitch || smsSwitch || emailSwitch;

        if (!isOn) {
            System.out.println("No notification channel enabled for type: " + notificationType);
            return;
        }

        System.out.println("Sending notifications for type: " + notificationType);

        if (Boolean.TRUE.equals(allSwitch)) {
            notifyViaEmail(notificationPreference.getEmail(), context, notificationType);
            notifyViaSms(notificationPreference.getPhoneNumber(), context, notificationType);
        } else {
            if (Boolean.TRUE.equals(smsSwitch)) {
                notifyViaSms(notificationPreference.getPhoneNumber(), context, notificationType);
            }

            if (Boolean.TRUE.equals(emailSwitch)) {
                notifyViaEmail(notificationPreference.getEmail(), context, notificationType);
            }
        }
    }



    private void notifyViaSms(String phoneNumber, Map<String, Object> context, String notificationType) {
        int  amount = (int) context.get("Amount");
        int  accNumber = (int) context.get("AccountNumber");
        String message = notificationType+ "is success. Account Number : "+accNumber+"Amount is : "+amount;
        if(!phoneNumber.isEmpty())
        {
            smsSender.send(phoneNumber,message);
        }
        else {
            System.out.println("Phone number not provided");
        }

    }

    private void notifyViaEmail(String email, Map<String, Object> context, String notificationType) {
        int  amount = (int) context.get("Amount");
        int  accNumber = (int) context.get("AccountNumber");
        String subject = "Notification on - "+notificationType;
        String body = notificationType+ "is success. Account Number : "+accNumber+"Amount is -"+amount;
        if(!email.isEmpty())
        {
            emailSender.send(email,subject,body);
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
