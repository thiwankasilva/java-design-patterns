package org.patterns.abstractFactory;

public class WindowsButton implements Button{
    @Override
    public void draw() {
        System.out.println("Drawing windows button");
    }
}
