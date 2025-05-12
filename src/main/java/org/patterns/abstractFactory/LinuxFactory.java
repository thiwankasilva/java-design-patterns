package org.patterns.abstractFactory;

public class LinuxFactory extends AbstractFactory{
    @Override
    public Button createButton() {
        return new LinuxButton();
    }

    @Override
    public TextBox createTextBox() {
        return new LinuxTextBox();
    }

    @Override
    public Dropdown createDropDown() {
        return new LinuxDropdown();
    }
}
