package org.patterns.mediator;

public class User {
    protected ChatRoom chatRoom;
    protected String name;

    public User(ChatRoom chatRoom, String name) {
        this.chatRoom = chatRoom;
        this.name = name;
        chatRoom.addUser(this);
    }

    public void receiveMessage(String msg)
    {
        System.out.println("received to "+name+" : "+msg);

    }


}
