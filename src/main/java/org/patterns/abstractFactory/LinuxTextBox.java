package org.patterns.abstractFactory;

public class LinuxTextBox implements TextBox{

    @Override
    public void draw() {
        System.out.println("Drawing Linux Text Box");
    }
}
