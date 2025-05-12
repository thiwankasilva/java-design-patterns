package org.patterns.mediator;

public interface ChatRoom {

    void sendMessage(String msg, User sender);

    void addUser(User user);

    void removeUser(User user);
}
