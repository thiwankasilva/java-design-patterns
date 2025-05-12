package org.patterns.observer;

import java.util.ArrayList;
import java.util.List;

public class MediaChannel {

    private final List<Subscriber> subscriberList = new ArrayList<>();

    public void distributeNews(String news)
    {
        for(Subscriber subscriber: subscriberList)
        {
            subscriber.onReceive(news);
        }

    }
    public void subscribe(Subscriber subscriber)
    {
        subscriberList.add(subscriber);
    }
    public void unSubscribe(Subscriber subscriber)
    {
        subscriberList.remove(subscriber);
    }
}
