package org.patterns.abstractFactory;

public class LinuxButton implements Button{
    @Override
    public void draw() {
        System.out.println("Drawing Linux Button");
    }
}
