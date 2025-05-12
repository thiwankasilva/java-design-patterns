package org.patterns.adapter;

import java.util.Map;

public interface NotificationService {

    void notify(String notificationType, Map<String, Object> context);
    void updatePhone(int accNumber, String phoneNumber);
    void updateEmail(int accNumber, String email);
    void updatePreference(int accNumber, String notificationType,String channel, boolean isOn);
}
