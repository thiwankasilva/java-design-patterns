package org.patterns.adapter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
                notifyViaEmail(notificationPreference.getEmail(),context,notificationType);
                notifyViaSms(notificationPreference.getPhoneNumber(),context,notificationType);
            } else if (notificationsSwitches.get(notificationType).get("sms")) {
                notifyViaSms(notificationPreference.getPhoneNumber(), context, notificationType);
            }
            else
            {
                notifyViaEmail(notificationPreference.getEmail(), context, notificationType);
            }
        }

    }

    private void notifyViaSms(String phoneNumber, Map<String, Object> context, String notificationType) {
        int  amount = (int) context.get("Amount");
        int  accNumber = (int) context.get("AccountNumber");
        String message = notificationType+ "is success. Account Number : "+accNumber+"Amount is -"+amount;
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
