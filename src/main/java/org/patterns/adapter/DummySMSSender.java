package org.patterns.adapter;

public class DummySMSSender implements SMSSender {

    @Override
    public void send(String phoneNo, String message) {
        System.out.println("Sending to : "+phoneNo+" "+message);
    }
}
