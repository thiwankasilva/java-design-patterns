package org.patterns.adapter;

import java.util.HashMap;
import java.util.Map;

public class InMemoryNotificationStore implements NotificationPreferenceStore{
    private Map<Integer, NotificationPreference> preferenceMap = new HashMap<>();
    @Override
    public NotificationPreference get(int accNumber) {
        if(!preferenceMap.containsKey(accNumber))
        {
            return new NotificationPreference(accNumber);
        }

        return preferenceMap.get(accNumber);
    }

    @Override
    public void save(NotificationPreference notificationPreference) {
        preferenceMap.put(notificationPreference.getAccNumber(),notificationPreference);
    }
}
