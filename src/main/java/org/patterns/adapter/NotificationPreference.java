package org.patterns.adapter;

import java.util.Map;

public class NotificationPreference {

    private int accNumber;
    private String phoneNumber;

    private String email;
    private Map<String, Boolean> notificationsSwitches;

    public NotificationPreference(int accNumber) {
        this.accNumber = accNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAccNumber() {
        return accNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<String, Boolean> getNotificationsSwitches() {
        return notificationsSwitches;
    }

    public void setNotificationsSwitches(Map<String, Boolean> notificationsSwitches) {
        this.notificationsSwitches = notificationsSwitches;
    }
}
