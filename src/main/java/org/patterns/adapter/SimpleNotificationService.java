package org.patterns.adapter;

import java.util.Map;
import java.util.Objects;

public class SimpleNotificationService implements NotificationService {

    private final NotificationPreferenceStore notificationPreferenceStore;

    public SimpleNotificationService(NotificationPreferenceStore notificationPreferenceStore) {
        this.notificationPreferenceStore = notificationPreferenceStore;
    }

    @Override
    public void notify(String notificationType, Map<String, Object> context) {
        System.out.println("NotificationType : "+notificationType+" "+context);
        int accNumber = (int) context.get("AccountNumber");
        int amount = (int) context.get("amount");
        boolean isOn = false;

        NotificationPreference notificationPreference = notificationPreferenceStore.get(accNumber);
        if(notificationPreference.getNotificationsSwitches().containsKey(notificationType))
        {
            isOn = notificationPreference.getNotificationsSwitches().get(notificationType);
        }
        String message = "";
        if(notificationType.equals("withdraw"))
        {
            message = amount +" is successfully withdrawed from account "+accNumber;
        }else if (notificationType.equals("deposit"))
        {
            message = amount +" is successfully deposited to account "+accNumber;
        }

        if(isOn)
        {
            if(notificationPreference.getPhoneNumber() != null)
            {
                System.out.println(message);
            }
            else if (notificationPreference.getEmail() != null)
            {
                System.out.println("sending Email");
            }

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
    public void updatePreference(int accNumber, String notificationType, boolean isOn) {
        NotificationPreference notificationPreference = notificationPreferenceStore.get(accNumber);
        if(notificationType.equals("all"))
        {
            notificationPreference.getNotificationsSwitches().put("withdraw",isOn);
            notificationPreference.getNotificationsSwitches().put("deposit",isOn);

        }else
        {
            notificationPreference.getNotificationsSwitches().put(notificationType,isOn);
        }

    }
}
