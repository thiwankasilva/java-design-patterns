package org.patterns.adapter;

public interface SMSSender {
    void send(String phoneNo, String message);
}
