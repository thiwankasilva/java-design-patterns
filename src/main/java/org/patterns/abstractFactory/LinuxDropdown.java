package org.patterns.abstractFactory;

public class LinuxDropdown implements Dropdown{
    @Override
    public void draw() {
        System.out.println("Drawing linux drop down");
    }
}
