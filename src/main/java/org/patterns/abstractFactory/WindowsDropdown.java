package org.patterns.abstractFactory;

public class WindowsDropdown implements Dropdown{
    @Override
    public void draw() {
        System.out.println("drawing windows dropdown");
    }
}
