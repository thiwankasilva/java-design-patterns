package org.patterns.mediator;

import java.util.LinkedList;

public class SimpleChatRoom implements ChatRoom{

    private final LinkedList<User> users = new LinkedList<>();
    @Override
    public void sendMessage(String msg, User sender) {
        for(User user: users)
        {
            user.receiveMessage(sender.name+" : "+msg);
        }

    }

    @Override
    public void addUser(User user) {
       users.add(user);
    }

    @Override
    public void removeUser(User user) {
       users.remove(user);
    }
}
