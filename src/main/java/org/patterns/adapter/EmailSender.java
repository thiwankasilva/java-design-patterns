package org.patterns.adapter;

public interface EmailSender {
    void send(String email, String subject, String body);
}
