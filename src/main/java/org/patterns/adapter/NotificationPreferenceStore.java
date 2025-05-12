package org.patterns.adapter;

public interface NotificationPreferenceStore {
    NotificationPreference get(int accNumber);
    void save(NotificationPreference notificationPreference);
}
