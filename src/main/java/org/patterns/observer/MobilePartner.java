package org.patterns.observer;

public class MobilePartner implements Subscriber{

    @Override
    public void onReceive(String news) {
        System.out.println("I am sending news via SMS :"+news);
    }
}
