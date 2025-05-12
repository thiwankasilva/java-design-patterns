package org.patterns.mediator;

public class MediatorMain {
    public static void main(String[] args) {
        ChatRoom chatRoom = new SimpleChatRoom();
        User thiwanka = new User(chatRoom,"Thiwanka");
        User hasini = new User(chatRoom,"Hasini");
        User hiruni = new User(chatRoom,"Hiruni");

        chatRoom.sendMessage("Hii Babe ",thiwanka);
    }
}
