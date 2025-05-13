package org.patterns.adapter;

import java.util.HashMap;
import java.util.Map;

public class NotificationPreference {

    private final int accNumber;
    private String phoneNumber;

    private String email;
    private final Map<String, HashMap<String,Boolean>> notificationsSwitches = new HashMap<>();

    public NotificationPreference(int accNumber) {
        this.accNumber = accNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAccNumber() {
        return accNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber != null ? phoneNumber : "";
    }

    public String getEmail() {
        return email != null ? email : "";
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<String, HashMap<String, Boolean>> getNotificationsSwitches() {
        return notificationsSwitches;
    }
}
