package org.patterns.abstractFactory;

public class WindowsTextBox implements TextBox{
    @Override
    public void draw() {
        System.out.println("Drawing a Windows TextBox");
    }
}
