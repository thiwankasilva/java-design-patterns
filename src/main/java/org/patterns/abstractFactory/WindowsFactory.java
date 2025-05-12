package org.patterns.abstractFactory;

public class WindowsFactory extends AbstractFactory{
    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public TextBox createTextBox() {
        return new WindowsTextBox();
    }

    @Override
    public Dropdown createDropDown() {
        return new WindowsDropdown();
    }
}
